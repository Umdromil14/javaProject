package tools.DBOutput;

import controller.TableEntry;
import interfaces.tableEntryCreator;
import tools.Address;

public class User implements tableEntryCreator {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String repeatPassword;
    private String salt;
    private Address address;
    private boolean isEmpty;
    private Integer idAddress;

    public User() {
        this.isEmpty = true;
    }

    public User(String firstname, String lastname, String eMail, String password, String street,String city,Integer number,Integer postalCode , String repeatPassword,String country)  
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = eMail;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
        this.repeatPassword = repeatPassword;
    }
    public User(Integer id ,Integer address,String firstname, String lastname, String password,String salt)  
    {
        this.id = id;
        this.idAddress = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.salt = salt;
    }
    public User(String firstname, String lastname, String eMail, String password,String repeatPassword, String street,String city, int number, int postalCode, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = eMail;
        this.repeatPassword = repeatPassword;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
    }
    public void setUser(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String password,
        String street,
        int number,
        int postalCode,
        String city,
        String country
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = new Address(street, number, city, postalCode, country);
        this.isEmpty = false;
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

    public Address getAddress() {
        return address;
    }

    public boolean isEmpty() {
        return isEmpty;
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void seteMail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
