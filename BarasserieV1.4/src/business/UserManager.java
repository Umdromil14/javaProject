package business;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import data.LocalityDBAccess;
import data.UserDBAccess;
import tools.DBOutput.TopProductClient;
import tools.DBOutput.User;

//import all méthods in controller

public class UserManager {
    private UserDBAccess userDBAccess;
    private LocalityDBAccess getComboBoxValues;
    public UserManager() {
        userDBAccess = new UserDBAccess();
        getComboBoxValues = new LocalityDBAccess();
    }
    //#region crud
    public void createUser(User user) throws SQLException
    {
        userDBAccess.create(user);
    }
    public void deleteUser(User user) throws SQLException
    {
        userDBAccess.delete(user);   
    }
    public void updateUser(User user) throws SQLException
    {
        userDBAccess.update(user);
    }
    public User getUser(int userId) throws SQLException {
        return userDBAccess.getUser(userId);
    }
    //#endregion

    //#region getComboBoxValues à changer ?
    public ArrayList<String> getCountries() throws SQLException {
        return getComboBoxValues.getCountries();
    }

    public ArrayList<Integer> getPostalCode(String city) throws SQLException {
        return getComboBoxValues.getPostalCode(city);
    }

    public ArrayList<String> getCity(String country) throws SQLException {
        return getComboBoxValues.getCity(country);
    }
    //#endregion

    public TopProductClient getTopProduct(int userId) throws SQLException {
        return userDBAccess.getTopProduct(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDBAccess.getAllUsers();
    }
}