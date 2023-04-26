

import javax.xml.bind.ValidationException;

public class BussinessEntity {
    private String firstname;
    private String lastname;
    private String eMail;
    private String password;
    private Address address;
    private String repeatPassword;

    public BussinessEntity(String firstname, String lastname, String eMail, String password, String street,String city,String number,String postalCode , String repeatPassword) throws ValidationException 
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eMail = eMail;
        this.password = password;
        this.address = new Address(street, number, city, postalCode);
        this.repeatPassword = repeatPassword;
    }

    public void seteMail(String eMail) throws ValidationException
    {
        try {
            this.eMail = Utils.isValidEmail(eMail);
        } catch (Exception e) {
            throw new ValidationException("Invalid email");
        }
    }
}
