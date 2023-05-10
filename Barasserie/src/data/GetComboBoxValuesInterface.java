package data;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.BusinessEntity;

public interface GetComboBoxValuesInterface {
    public ArrayList<String> getCountries() throws SQLException;
    public ArrayList<Integer> getPostalCode(String city) throws SQLException;
    public ArrayList<String> getCity(String country) throws SQLException;
    public BusinessEntity getUser(int id) throws SQLException;
}
