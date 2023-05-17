package business;

import exception.DataAccessException;
import java.util.List;

import data.AddressDBAccess;
import interfaces.AddressDataAccess;
import model.City;
import model.TopProductCity;

public class AddressManager {
    private AddressDataAccess dao;

    public AddressManager() {
        this.dao = new AddressDBAccess();
    }

    public List<City> getAllCities() throws DataAccessException {
        return dao.getAllCities();
    }

    public TopProductCity getTopProduct(int cityId) throws DataAccessException {
        return dao.getTopProduct(cityId);
    }

    public List<String> getCountries() throws DataAccessException {
        return dao.getCountries();
    }

    public List<Integer> getPostalCode(String city) throws DataAccessException {
        return dao.getPostalCode(city);
    }

    public List<String> getCity(String country) throws DataAccessException {
        return dao.getCity(country);
    }
}

