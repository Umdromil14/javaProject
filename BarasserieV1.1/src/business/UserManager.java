package business;

import java.sql.SQLException;

import data.UserDBAccess;
import tools.*;
import tools.exception.*;

public class UserManager 
{
    private UserDBAccess userDBAccess;

    public UserManager() 
    {
        setUserDBAccess(new UserDBAccess());
    }
   public void setUserDBAccess(UserDBAccess userDBAccess) 
   {
       this.userDBAccess = userDBAccess;
   }
    public void addUser(BusinessEntity user) throws AddUserException, SQLException 
    {
        //check if user is valid
        if (!filterDataUser(user))
            throw new AddUserException("Invalid user");
        userDBAccess.addUser(user);
    }
    public void connectUser(String eMail, String password) throws SQLException, ConnectUserException
    {
        if (eMail.isEmpty() || password.isEmpty())
            throw new ConnectUserException("Invalid user");
        userDBAccess.connectUser(eMail, password);
    }
    public Boolean filterDataUser(BusinessEntity bussinessEntity)
    {
        if (!isSamePassword(bussinessEntity.getPassword(), bussinessEntity.getRepeatPassword()))
        //throw exception
            return false;
        if (isFieldEmpty(bussinessEntity))
        //throw exception
            return false;
        if (!Utils.isValidEmail(bussinessEntity.geteMail()))
        //throw exception
            return false;
        return true;
    }
    public Boolean isSamePassword(String password, String repeatPassword)
    {
        return password.equals(repeatPassword);
    }
    public Boolean isFieldEmpty(BusinessEntity bussinessEntity)
    {
        return bussinessEntity.getFirstname().isEmpty() || 
        bussinessEntity.getLastname().isEmpty() || 
        bussinessEntity.geteMail().isEmpty() || 
        bussinessEntity.getPassword().isEmpty() ||
        bussinessEntity.getRepeatPassword().isEmpty()||
        bussinessEntity.getAddress().getCity().isEmpty() || 
        bussinessEntity.getAddress().getStreet().isEmpty() || 
        bussinessEntity.getAddress().getNumber() == null || 
        bussinessEntity.getAddress().getPostalCode() == null;
    }
}
