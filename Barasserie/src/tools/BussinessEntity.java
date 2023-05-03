package tools;

import java.sql.Date;

public class BussinessEntity {
    private String firstname;
    private String lastname;
    private String eMail;
    private String password;
    private Address address;
    private String repeatPassword;
    private Integer id;
    private String salt;
    private Integer idAddress;

    

    public BussinessEntity(String firstname, String lastname, String eMail, String password, String street,String city,Integer number,Integer postalCode , String repeatPassword,String country)  
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eMail = eMail;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
        this.repeatPassword = repeatPassword;
    }
    public BussinessEntity(Integer id ,Integer address,String firstname, String lastname, String password,String salt)  
    {
        this.id = id;
        this.idAddress = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.salt = salt;
    }
    public BussinessEntity(String firstname, String lastname, String eMail, String password,String repeatPassword, String street,String city, int number, int postalCode, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eMail = eMail;
        this.repeatPassword = repeatPassword;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
    }
	public Integer getId() {
        return id;
    }
    public String getSalt() {
        return salt;
    }
    public Address getAddress() {
        return address;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPassword() {
        return password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public String getEmail() {
        return eMail;
    }
    public Integer getIdAddress() {
        return idAddress;
    }
    

}
