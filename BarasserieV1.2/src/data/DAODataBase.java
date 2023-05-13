package data;

import java.sql.SQLException;

import tools.DBOutput.User;


public interface DAODataBase {
    public void create(User bussinessEntity) throws SQLException;
    public void read() throws SQLException;
    public void update(User bussinessEntity,Integer id,Integer idAdress) throws SQLException;
    public void delete(Integer id,Integer idAdress) throws SQLException;
}
