package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import data.DBResearch;
import tools.DBOutput.*;
import tools.exception.InvalidInputException;

public class ResearchManager {
    private DBResearch dao;

    public ResearchManager() {
        this.dao = new DBResearch();
    }

    public TableEntry getTopProductByCityId(int cityId) throws InvalidInputException, SQLException, NoSuchElementException {
        validateInput(cityId);
        TopProductCity topProductCity = dao.getTopProductByCityId(cityId);

        if (topProductCity == null) {
            throw new NoSuchElementException("No product found with this ID");
        }

        return topProductCity.toTableEntry();
    }

    public TableEntry getTopProductByClientId(int clientId) throws InvalidInputException, SQLException, NoSuchElementException {
        validateInput(clientId);
        TopProductClient topProductClient = dao.getTopProductByClientId(clientId);

        if (topProductClient == null) {
            throw new NoSuchElementException("No product found with this ID");
        }

        return topProductClient.toTableEntry();
    }

    public TableEntry getUserById(int clientId) throws InvalidInputException, SQLException, NoSuchElementException {
        validateInput(clientId);
        User user = dao.getUserById(clientId);

        if (user == null) {
            throw new NoSuchElementException("No user found with this ID");
        }

        return user.toTableEntry();
    }

    public void validateInput(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("The ID must be a positive integer");
        }
    }

    public List<TableEntry> getUsers() throws SQLException {
        List<User> users = dao.getUsers();
        List<TableEntry> tableEntries = new ArrayList<>();
        
        for (User user : users) {
            tableEntries.add(user.toTableEntry());
        }

        return tableEntries;
    }

    public List<TableEntry> getCities() throws SQLException {
        List<City> cities = dao.getCities();
        List<TableEntry> tableEntries = new ArrayList<>();
        
        for (City city : cities) {
            tableEntries.add(city.toTableEntry());
        }

        return tableEntries;
    }
}
