package main.model;

import main.controller.TableEntry;
import main.interfaces.tableEntryCreator;

public class User implements tableEntryCreator {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String salt;
    private Address address;
    private boolean isEmpty;
    private Integer idAddress;

    public User() {
        this.isEmpty = true;
    }

    public User(Integer id, String firstname, String lastname, String email, String password, String salt, Address address, Integer idAddress, String repeatPassword) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.address = address;
        this.idAddress = idAddress;
        this.isEmpty = false;
    }

    public User(String firstname, String lastname, String eMail, String password, String street, String city, Integer number, Integer postalCode, String repeatPassword, String country) {
        this(null, firstname, lastname, eMail, password, null , new Address(street, number, city, postalCode, country), null, repeatPassword);
    }

    public void setUser(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String password,
        String salt,
        String street,
        int number,
        int postalCode,
        String city,
        String country,
        Integer idAddress
    ) 
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = new Address(street, number, city, postalCode, country);
        this.idAddress = idAddress;
        this.salt = salt;
        this.isEmpty = false;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public Integer getId() {
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

    public String getSalt() {
        return salt;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
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
