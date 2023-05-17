package tools.DBOutput;


public class Address {
    private Integer id;
    private String street;
    private Integer number;
    private City city;

    public Address(Integer id, String street, Integer number, String city, Integer postalCode, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = new City(city, postalCode, country);
    }

    public Address(String street, Integer number, String city, Integer postalCode, String country) {
        this(null, street, number, city, postalCode, country);
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public City getCity() {
        return city;
    }

}
