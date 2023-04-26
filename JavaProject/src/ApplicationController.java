import java.sql.SQLException;

public class ApplicationController 
{
    private UserManager userManager;

    public ApplicationController() 
    {
        setUserManager(new UserManager());
    }
    public void setUserManager(UserManager userManager) 
    {
        this.userManager = userManager;
    }
    public void addUser(BussinessEntity user) throws AddUserException, SQLException 
    {
        userManager.addUser(user);
    }
}
