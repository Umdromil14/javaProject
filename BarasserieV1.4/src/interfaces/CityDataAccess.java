<<<<<<< HEAD
package interfaces;

import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public interface CityDataAccess {
    public TopProductCity getTopProduct(int cityId) throws SQLException;

    public List<City> getAllCities() throws SQLException;
}
=======
package interfaces;

import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public interface CityDataAccess {
    public TopProductCity getTopProduct(int cityId) throws SQLException;

    public List<City> getAllCities() throws SQLException;
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
