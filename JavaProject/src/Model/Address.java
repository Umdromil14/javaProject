package Model;

import javax.xml.bind.ValidationException;

public class Address {
    private String street;
    private Integer number;
    private String city;
    private Integer postalCode;

    public Address(String street, String number, String city, String postalCode) throws ValidationException 
    {
        this.street = street;
        setNumber(number);
        this.city = city;
        setPostalCode(postalCode);
    }

    public void setNumber(String number) throws ValidationException 
    {
        try 
        {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number");
        }
    }
    public void setPostalCode(String postalCode) throws ValidationException {
        //check if the postal code is valid
        try 
        {
            this.postalCode = Integer.parseInt(postalCode);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number");
        }
        
    }
    public void setStreet(String street) 
    {
        //check if the street is valid
        this.street = street;
    }
}
