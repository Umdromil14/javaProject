package tools.DBOutput;

import tools.Address;

public class User implements tableEntryCreator {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Address address;

    public User(int id, String firstname, String lastname, String email, String password, String street, int number, int postalCode, String city, String country) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = new Address(street, number, city, postalCode, country);
    }

    public User(int id, String firstname, String lastname, String email, String street, int number, int postalCode, String city, String country) {
        this(id, firstname, lastname, email, null, street, number, postalCode, city, country);
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public TableEntry toTableEntry() {
        return new TableEntry(
            Integer.toString(id),
            firstname,
            lastname,
            email,
            address.getStreet(),
            Integer.toString(address.getNumber()),
            Integer.toString(address.getCity().getPostalCode()),
            address.getCity().getName(),
            address.getCity().getCountry()
        );
    }
}
