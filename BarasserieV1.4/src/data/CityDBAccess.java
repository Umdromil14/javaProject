<<<<<<< HEAD
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.CityDataAccess;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;


public class CityDBAccess implements CityDataAccess {
    private Connection connection;
    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();

        String query = "SELECT * FROM city";
        
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("postalCode"),
                        resultSet.getString("country")
                    ));
                }
            }
        }

        return cities;
    }

    public TopProductCity getTopProduct(int cityId) throws SQLException {
        TopProductCity topProductCity = new TopProductCity();

        String query = new StringBuilder()
            .append("SELECT c.name, p.name, SUM(td.quantity) AS quantity ")
            .append("FROM city c ")
            .append("INNER JOIN address a ON c.id = a.city ")
            .append("INNER JOIN business_entity be ON a.id = be.address ")
            .append("INNER JOIN workflow w ON be.id = w.agent ")
            .append("INNER JOIN document d ON w.id = d.workflow ")
            .append("INNER JOIN transaction_detail td ON d.docType = td.docType AND d.id = td.document ")
            .append("INNER JOIN product p ON td.product = p.id ")
            .append("WHERE c.id = ? ")
            .append("GROUP BY p.name ")
            .append("ORDER BY SUM(td.quantity) DESC ")
            .append("LIMIT 1")
            .toString();

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cityId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    topProductCity.setTopProductCity(
                        resultSet.getString("c.name"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("quantity")
                    );
                }
            }
        }

        return topProductCity;
    }
}
=======
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.CityDataAccess;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;


public class CityDBAccess implements CityDataAccess {
    private Connection connection;
    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();

        String query = "SELECT * FROM city";
        
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("postalCode"),
                        resultSet.getString("country")
                    ));
                }
            }
        }

        return cities;
    }

    public TopProductCity getTopProduct(int cityId) throws SQLException {
        TopProductCity topProductCity = new TopProductCity();

        String query = new StringBuilder()
            .append("SELECT c.name, p.name, SUM(td.quantity) AS quantity ")
            .append("FROM city c ")
            .append("JOIN address a ON c.id = a.city ")
            .append("JOIN business_entity be ON a.id = be.address ")
            .append("JOIN workflow w ON be.id = w.agent ")
            .append("JOIN document d ON w.id = d.workflow ")
            .append("JOIN transaction_detail td ON d.docType = td.docType AND d.id = td.document ")
            .append("JOIN product p ON td.product = p.id ")
            .append("WHERE c.id = ? ")
            .append("GROUP BY p.name ")
            .append("ORDER BY SUM(td.quantity) DESC ")
            .append("LIMIT 1")
            .toString();

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cityId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    topProductCity.setTopProductCity(
                        resultSet.getString("c.name"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("quantity")
                    );
                }
            }
        }

        return topProductCity;
    }
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519