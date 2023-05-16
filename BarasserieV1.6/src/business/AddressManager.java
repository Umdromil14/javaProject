package business;

import java.sql.SQLException;
import java.util.List;

import data.AddressDBAccess;
import interfaces.AddressDataAccess;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public class AddressManager {
    private AddressDataAccess dao;

    public AddressManager() {
        this.dao = new AddressDBAccess();
    }

    public List<City> getAllCities() throws SQLException {
        return dao.getAllCities();
    }

    public TopProductCity getTopProduct(int cityId) throws SQLException {
        return dao.getTopProduct(cityId);
    }

    public List<String> getCountries() throws SQLException {
        return dao.getCountries();
    }

    public List<Integer> getPostalCode(String city) throws SQLException {
        return dao.getPostalCode(city);
    }

    public List<String> getCity(String country) throws SQLException {
        return dao.getCity(country);
    }
}

