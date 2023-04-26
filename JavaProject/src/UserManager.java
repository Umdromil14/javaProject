

import java.sql.SQLException;

import Utils.Utils;
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
    public void addUser(BussinessEntity user)throws AddUserException, SQLException 
    {
        //check if user is valid
        if (user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.geteMail().isEmpty() || user.getPassword().isEmpty() ||
        user.getRepeatPassword().isEmpty()|| user.getAddress().getCity().isEmpty() || user.getAddress().getStreet().isEmpty() || user.getAddress().getNumber() == null || 
        user.getAddress().getPostalCode() == null)
            throw new AddUserException("Empty fields");
        if (!Utils.isValidEmail(user.geteMail()))
            throw new AddUserException("Invalid email");
        if (!user.getPassword().equals(user.getRepeatPassword()))
            throw new AddUserException("Passwords don't match");
        System.out.println("User is valid");
        userDBAccess.addUser(user);
    }
}
