package business;

import java.sql.SQLException;

import tools.BusinessEntity;

public interface UserManagerInterface {
    public void createUser(BusinessEntity bussinessEntity) throws SQLException,tools.exception.AddUserException, tools.exception.AddUserException;
    public void deleteUser(Integer id,Integer idAdress) throws SQLException;
    public void updateUser(BusinessEntity bussinessEntity) throws SQLException, tools.exception.AddUserException;
    
}
