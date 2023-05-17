package interfaces;

import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public interface AddressDataAccess {
    public TopProductCity getTopProduct(int cityId) throws SQLException;

    public List<City> getAllCities() throws SQLException;

    public List<String> getCountries() throws SQLException;

    public List<Integer> getPostalCode(String city) throws SQLException;

    public List<String> getCity(String country) throws SQLException;

    public void deleteAddress(Integer idAddress) throws SQLException;
}
