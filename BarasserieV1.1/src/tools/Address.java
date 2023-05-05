package tools;

import tools.DBOutput.City;

public class Address {
    private String street;
    private Integer number;
    private City city;

    public Address(String street, Integer number, String city, Integer postalCode, String country) {
        this.street = street;
        this.number = number;
        this.city = new City(city, postalCode, country);
    }
    
    public String getCity() {
        return city.getName();
    }
    public Integer getNumber() {
        return number;
    }
    public Integer getPostalCode() {
        return city.getPostalCode();
    }
    public String getStreet() {
        return street;
    }
    public String getCountry() {
        return city.getCountry();
    }
}
