package data;

import java.sql.SQLException;

import tools.BusinessEntity;

public interface DAODataBase {
    public void create(BusinessEntity bussinessEntity) throws SQLException;
    public void read() throws SQLException;
    public void update(BusinessEntity bussinessEntity,Integer id,Integer idAdress) throws SQLException;
    public void delete(Integer id,Integer idAdress) throws SQLException;
}
