<<<<<<< HEAD
package interfaces;
import java.sql.SQLException;
import java.util.ArrayList;



public interface localityDataAccess {
    public ArrayList<String> getCountries() throws SQLException;
    public ArrayList<Integer> getPostalCode(String city) throws SQLException;
    public ArrayList<String> getCity(String country) throws SQLException;
=======
package interfaces;
import java.sql.SQLException;
import java.util.ArrayList;



public interface localityDataAccess {
    public ArrayList<String> getCountries() throws SQLException;
    public ArrayList<Integer> getPostalCode(String city) throws SQLException;
    public ArrayList<String> getCity(String country) throws SQLException;
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
}