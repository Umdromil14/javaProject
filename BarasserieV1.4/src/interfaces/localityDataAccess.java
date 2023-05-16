package interfaces;
import java.sql.SQLException;
import java.util.ArrayList;



public interface localityDataAccess {
    public ArrayList<String> getCountries() throws SQLException;
    public ArrayList<Integer> getPostalCode(String city) throws SQLException;
    public ArrayList<String> getCity(String country) throws SQLException;
}