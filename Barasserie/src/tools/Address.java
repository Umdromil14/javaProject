package tools;

import tools.DBOutput.City;

public class Address {
    private String street;
    private Integer number;
    private City city;
    private Integer postalCode;
    private String country;
    private Integer id;

    public Address(String street, Integer number, String city, Integer postalCode, String country) {
        this.street = street;
        this.number = number;
        this.city = new City(city, postalCode, country);
    }
    public Address(Integer id,String street, Integer number, String city, Integer postalCode, String country) 
    {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = new City(city, postalCode, country);
    }
    public City getCity() {
        return city;
    }
    public Integer getNumber() {
        return number;
    }
    public String getStreet() {
        return street;
    }
    public Integer getId() {
        return id;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    @Override
    public String toString() {
        return "Address [city=" + city + ", country=" + country + ", number=" + number + ", postalCode=" + postalCode
                + ", street=" + street + "]";
    }
}
