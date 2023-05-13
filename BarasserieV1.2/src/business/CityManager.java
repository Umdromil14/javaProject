package business;

import java.sql.SQLException;
import java.util.List;

import data.CityDBAccess;
import interfaces.CityDataAccess;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public class CityManager {
    private CityDataAccess dao;

    public CityManager() {
        this.dao = new CityDBAccess();
    }

    public List<City> getAllCities() throws SQLException {
        return dao.getAllCities();
    }

    public TopProductCity getTopProduct(int cityId) throws SQLException {
        return dao.getTopProduct(cityId);
    }
}
