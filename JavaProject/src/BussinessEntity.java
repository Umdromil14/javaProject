

public class BussinessEntity {
    private String firstname;
    private String lastname;
    private String eMail;
    private String password;
    private Address address;
    private String repeatPassword;
    

    public BussinessEntity(String firstname, String lastname, String eMail, String password, String street,String city,Integer number,Integer postalCode , String repeatPassword,String country)  
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eMail = eMail;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
        this.repeatPassword = repeatPassword;
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
    public String geteMail() {
        return eMail;
    }

}
