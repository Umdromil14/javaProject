package data;

import java.sql.SQLException;

import tools.BussinessEntity;

public interface DAODataBase {
    public void create(BussinessEntity bussinessEntity) throws SQLException;
    public void read() throws SQLException;
    public void update(BussinessEntity bussinessEntity,Integer id,Integer idAdress) throws SQLException;
    public void delete(Integer id,Integer idAdress) throws SQLException;
}
