package data;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.DBOutput.User;

public interface GetComboBoxValuesInterface {
    public ArrayList<String> getCountries() throws SQLException;
    public ArrayList<Integer> getPostalCode(String city) throws SQLException;
    public ArrayList<String> getCity(String country) throws SQLException;
    public User getUser(int id) throws SQLException;
}