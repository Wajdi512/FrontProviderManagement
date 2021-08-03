package portfolioManagement;

public class Habitation {

    private final String number,street,city,postcode,region,country;

    public Habitation(String number, String street, String city, String postcode, String region, String country){
        this.number = number;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.region = region;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getNumber() {
        return number;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getRegion() {
        return region;
    }

    public String getStreet() {
        return street;
    }
}
