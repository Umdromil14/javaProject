package business;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import data.DAODataBase;
import data.DBClient;
import data.UserDBAccess;
import interfaces.UserDataAccess;
import tools.*;
import tools.DBOutput.TopProductClient;
import tools.DBOutput.User;

public class UserManager {
    private DAODataBase dao;
    private UserDataAccess userDBAccess;

    public UserManager() {
        dao = new DBClient();
        userDBAccess = new UserDBAccess();
    }

    public void createUser(User user) throws SQLException
    {
        //all the filter to add
        if (filterDataUser(user))
        {
                dao.create(user);
        }
        else
        {
            throw new SQLException("Invalid data");
        }

    }
    public void deleteUser(Integer id,Integer idAdress) throws SQLException
    {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Delete user", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) 
        {
            dao.delete(id,idAdress);   
        }
    }
    public void updateUser(User bussinessEntity) throws SQLException
    {
        //all the filter to update
        try
        {
            if (filterDataUser(bussinessEntity))
            {
                dao.update(bussinessEntity,bussinessEntity.getId(),bussinessEntity.getAddress().getId());
            }
        }
        catch (SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
    }
        
    public Boolean filterDataUser(User bussinessEntity)
    {
        return isSamePassword(bussinessEntity.getPassword(), bussinessEntity.getRepeatPassword()) && 
        !isFieldEmpty(bussinessEntity)&& 
        isValidEmail(bussinessEntity.getEmail());
    }
    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
    public Boolean isSamePassword(String password, String repeatPassword)
    {
        return password.equals(repeatPassword);
    }
    public Boolean isFieldEmpty(User bussinessEntity)
    {
        
        return bussinessEntity.getFirstname().isEmpty() || 
        bussinessEntity.getLastname().isEmpty() || 
        bussinessEntity.getEmail().isEmpty() || 
        bussinessEntity.getPassword().isEmpty() ||
        bussinessEntity.getRepeatPassword().isEmpty()||
        bussinessEntity.getAddress().getCity().getName().isEmpty() || 
        bussinessEntity.getAddress().getStreet().isEmpty() || 
        bussinessEntity.getAddress().getNumber() == null || 
        bussinessEntity.getAddress().getCity().getPostalCode() == null;
    }

    public TopProductClient getTopProduct(int userId) throws SQLException {
        return userDBAccess.getTopProduct(userId);
    }

    public User getUser(int userId) throws SQLException {
        return userDBAccess.getUser(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDBAccess.getAllUsers();
    }
}