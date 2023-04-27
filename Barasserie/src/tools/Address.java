package tools;

public class Address {
    private String street;
    private Integer number;
    private String city;
    private Integer postalCode;
    private String country;

    public Address(String street, Integer number, String city, Integer postalCode, String country) 
    {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public Integer getNumber() {
        return number;
    }
    public Integer getPostalCode() {
        return postalCode;
    }
    public String getStreet() {
        return street;
    }
    public String getCountry() {
        return country;
    }
}
