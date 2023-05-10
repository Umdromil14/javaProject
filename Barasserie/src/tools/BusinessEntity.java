package tools;

public class BusinessEntity {
    private String firstname;
    private String lastname;
    private String eMail;
    private String password;
    private Address address;
    private String repeatPassword;
    private Integer id;
    private String salt;
    private Integer idAddress;

    

    public BusinessEntity(String firstname, String lastname, String eMail, String password, String street,String city,Integer number,Integer postalCode , String repeatPassword,String country)  
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eMail = eMail;
        this.password = password;
        this.address = new Address(street, number, city, postalCode,country);
        this.repeatPassword = repeatPassword;
    }
    public BusinessEntity()
    {}
    public BusinessEntity(Integer id ,Integer address,String firstname, String lastname, String password,String salt)  
    {
        this.id = id;
        this.idAddress = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.salt = salt;
    }
    public BusinessEntity(String firstname, String lastname, String eMail, String password,String repeatPassword, String street,String city, int number, int postalCode, String country) {
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
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    @Override
    public String toString() {
        return "BusinessEntity [address=" + address + ", eMail=" + eMail + ", firstname=" + firstname + ", id=" + id
                + ", lastname=" + lastname + ", password=" + password + ", repeatPassword=" + repeatPassword + ", salt="
                + salt + "]";
    }

}
