package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.CityDataAccess;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;

public class CityDBAccess implements CityDataAccess {
    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();

        String query = "SELECT * FROM city";
        
        Connection connection = SingletonConnection.getInstance();

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

        Connection connection = SingletonConnection.getInstance();

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
