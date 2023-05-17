package interfaces;

import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.*;

public interface AddressDataAccess {
    public TopProductCity getTopProduct(int cityId) throws SQLException;

    public List<City> getAllCities() throws SQLException;

    public List<String> getCountries() throws SQLException;

    public List<Integer> getPostalCode(String city) throws SQLException;

    public List<String> getCity(String country) throws SQLException;

    public void deleteAddress(Integer idAddress) throws SQLException;

    public Integer getAddress(Address address) throws SQLException;
    
    public Integer addAddress(Address address) throws SQLException ;
}
