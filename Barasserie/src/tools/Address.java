package tools;

public class Address {
    private String street;
    private Integer number;
    private String city;
    private Integer postalCode;
    private String country;
    private Integer id;

    public Address(String street, Integer number, String city, Integer postalCode, String country) 
    {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
    public Address(Integer id,String street, Integer number, String city, Integer postalCode, String country) 
    {
        this.id = id;
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
    public Integer getId() {
        return id;
    }
}
