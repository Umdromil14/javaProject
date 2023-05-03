package business;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.DAODataBase;
import data.DBClient;
import exception.AddUserException;
import tools.*;

public class UserManager 
{
    private DAODataBase dao;
    public UserManager()
    {
        dao = new DBClient();
    }

    public void createUser(BussinessEntity bussinessEntity) throws SQLException, AddUserException
    {
        //all the filter to add
        try
        {
            if (filterDataUser(bussinessEntity))
            {
                dao.create(bussinessEntity);
            }
        }
        catch (SQLException e)
        {
            throw new AddUserException("Error in the data");
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
    public void updateUser(BussinessEntity bussinessEntity) throws SQLException, AddUserException
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
            throw new AddUserException("Error in the data");
        }
    }
        
    public Boolean filterDataUser(BussinessEntity bussinessEntity) throws AddUserException
    {
        if (!isSamePassword(bussinessEntity.getPassword(), bussinessEntity.getRepeatPassword()))
            throw new AddUserException("The passwords are not the same");
        if (isFieldEmpty(bussinessEntity))
            throw new AddUserException("There are empty fields");
        if (!isValidEmail(bussinessEntity.getEmail()))
            throw new AddUserException("The email is not valid");
         return true;
    }
    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }   
    public Boolean isSamePassword(String password, String repeatPassword)
    {
        return password.equals(repeatPassword);
    }
    public Boolean isFieldEmpty(BussinessEntity bussinessEntity)
    {
        return bussinessEntity.getFirstname().isEmpty() || 
        bussinessEntity.getLastname().isEmpty() || 
        bussinessEntity.getEmail().isEmpty() || 
        bussinessEntity.getPassword().isEmpty() ||
        bussinessEntity.getRepeatPassword().isEmpty()||
        bussinessEntity.getAddress().getCity().isEmpty() || 
        bussinessEntity.getAddress().getStreet().isEmpty() || 
        bussinessEntity.getAddress().getNumber() == null || 
        bussinessEntity.getAddress().getPostalCode() == null;
    }
}
