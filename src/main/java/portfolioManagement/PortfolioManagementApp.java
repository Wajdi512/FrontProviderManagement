package portfolioManagement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PortfolioManagementApp {

    private  ArrayList<Portfolio> portfolios = new ArrayList<>();
    private Portfolio currentPortfolio;
    private User user;
    private final String serverAddress = "http://localhost:8080/";


    /**
     * Send a request to the server
     * @param url the url
     * @return the server response
     * @throws IOException exception
     */
    private String getMethod(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
            }
            scanner.close();
            return response;
        }
        return null;
    }


    /**
     * Send data to the server
     * @param url the url
     * @param data the data to send
     * @return true if the data as been inserted in the database, false otherwise
     * @throws IOException exception
     */
    private boolean postMethod(String url, JSONObject data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        String dataString = data.toString();
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(dataString);
        writer.flush();
        int respondCode = connection.getResponseCode();
        return respondCode == 200;
    }


    /**
     * Create a new portfolio
     * @param portfolio a portfolio object which contain the name of the portfolio and the habitation
     * @return true if the portfolio has been created, false otherwise
     * @throws IOException exception
     */
    public boolean createPortfolio(Portfolio portfolio) throws IOException {
        String idResponse = getMethod(serverAddress + "/id/portfolio");
        if (idResponse != null){
            JSONObject jsonId = new JSONObject(idResponse);
            String id = jsonId.getString("id");
            portfolio.setId(id);
            portfolio.setAdmin(user.getMail());

            String url = serverAddress + "new/portfolio";
            JSONObject newPortfolio = new JSONObject();
            newPortfolio.put("id",id);
            newPortfolio.put("name", portfolio.getName());
            newPortfolio.put("admin",user.getMail());
            if (postMethod(url,newPortfolio)){
                portfolios.add(portfolio);
                return true;
            }
        }
        return false;

    }

    public boolean addSupplyPoint(SupplyPoint supplyPoint) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",currentPortfolio.getId());
        jsonObject.put("ean",supplyPoint.getEan());
        jsonObject.put("supplier",supplyPoint.getSupplierName());
        jsonObject.put("energy",supplyPoint.getEnergy());
        String url = serverAddress+"/supplyPoint";
        if (postMethod(url,jsonObject)){
            currentPortfolio.addSupplyPoint(supplyPoint);
            return true;
        }
        return false;
    }

    public void getSupplyPointPortfolio(String id){
        try{
            String response = getMethod(serverAddress + "/portfolio?id="+id);
            if (response != null){
                JSONObject jsonObject = new JSONObject(response);
                System.out.println(jsonObject);
                JSONArray supplyPointsArray = jsonObject.getJSONArray("supplyPoints");
                for (int i=0; i < supplyPointsArray.length();i++){
                    JSONObject supplyPoint = supplyPointsArray.getJSONObject(i);
                    String ean = supplyPoint.getString("ean");
                    String energy = supplyPoint.getString("energy");
                    String supplier = supplyPoint.getString("supplier");
                    SupplyPoint s = new SupplyPoint(ean,supplier,stringToEnergy(energy));
                    currentPortfolio.addSupplyPoint(s);
                }
            }
        }catch (IOException e){

        }

    }

    private Energy stringToEnergy(String energy){
        switch (energy){
            case "electricity":
                return Energy.electricity;
            case "water":
                return Energy.water;
            case "gas":
                return Energy.gas;
            default:
                return null;
        }
    }

    public ArrayList<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setCurrentPortfolio(Portfolio currentPortfolio) {
        this.currentPortfolio = currentPortfolio;
    }

    public Portfolio getCurrentPortfolio() {
        return currentPortfolio;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void loadPortfolio(Portfolio portfolio){
        portfolios.add(portfolio);
    }
}
