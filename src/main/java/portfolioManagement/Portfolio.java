package portfolioManagement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Portfolio {

    private String id;
    private String name;
    private String admin;
    private Habitation habitation;

    private ArrayList<SupplyPoint> supplyPoints = new ArrayList<>();

    public Portfolio(JSONObject portfolio){
        id = portfolio.getString("id");
        name = portfolio.getString("name");
        admin = portfolio.getString("admin");
    }

    public Portfolio(String name, Habitation habitation){
        this.name = name;
        this.habitation = habitation;
    }

    public Portfolio(String id, String name, String admin){
        this.id = id;
        this.name = name;
        this.admin =admin;
    }

    public void addSupplyPoint(SupplyPoint supplyPoint){
        supplyPoints.add(supplyPoint);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public ArrayList<SupplyPoint> getSupplyPoints() {
        return supplyPoints;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setId(String id) {
        this.id = id;
    }
}
