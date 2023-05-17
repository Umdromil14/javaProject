package business;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.DAODataBase;
import data.DBClient;
import tools.*;

public class UserManager implements UserManagerInterface
{
    private DAODataBase dao;
    public UserManager()
    {
        dao = new DBClient();
    }

    public void createUser(BusinessEntity user) throws SQLException, tools.exception.AddUserException
    {
        //all the filter to add
        if (filterDataUser(user))
        {
                dao.create(user);
        }
        else
        {
            throw new tools.exception.AddUserException("Error in the data");
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
    public void updateUser(BusinessEntity bussinessEntity) throws SQLException, tools.exception.AddUserException
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
            throw new tools.exception.AddUserException("Error in the data");
        }
    }
        
    public Boolean filterDataUser(BusinessEntity bussinessEntity) throws tools.exception.AddUserException
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
    public Boolean isFieldEmpty(BusinessEntity bussinessEntity)
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
}
