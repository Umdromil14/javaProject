package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.AddressDataAccess;
import tools.DBOutput.Address;
import tools.DBOutput.City;
import tools.DBOutput.TopProductCity;


public class AddressDBAccess implements AddressDataAccess {
    private Connection connection;

    @Override
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

    @Override
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

    @Override
    public List<String> getCountries() throws SQLException {
        List<String> countries = new ArrayList<>();

        String query = "SELECT DISTINCT name FROM country";
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    countries.add(rs.getString("name"));
                }
            }
        }

        return countries;
    }

    @Override
    public List<Integer> getPostalCode(String city) throws SQLException {
        List<Integer> postalCode = new ArrayList<>();

        String query = "SELECT DISTINCT postalCode FROM city where name = ?";
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, city);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    postalCode.add(rs.getInt("postalCode"));
                }
            }
        }
        return postalCode;
    }

    @Override
    public List<String> getCity(String country) throws SQLException {
        List<String> city = new ArrayList<String>();

        String query = "SELECT DISTINCT name FROM city where country = ?";
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, country);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    city.add(rs.getString("name"));
                }
            }
        }
        return city;
    }

    @Override
    public void deleteAddress(Integer idAddress) throws SQLException {
        String query = "DELETE FROM address WHERE id = ?";
        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAddress);
            statement.executeUpdate();
        }
    }

    public Integer getAddress(Address address) throws SQLException {
        Integer cityId = null;
        String query = "SELECT id FROM city WHERE name = ? AND postalCode = ? AND country = ?";

        connection = SingletonConnection.getInstance();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, address.getCity().getName());
            statement.setInt(2, address.getCity().getPostalCode());
            statement.setString(3, address.getCity().getCountry());

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cityId = rs.getInt("id");
                }
            }
        }
        return cityId;
    }

    public Integer addAddress(Address address) throws SQLException {
        Integer cityId = getAddress(address);
        Integer addressId = null;

        if (cityId != null) {
            String query = "INSERT INTO address (street, number, city) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, address.getStreet());
                statement.setInt(2, address.getNumber());
                statement.setInt(3, cityId);

                int affectedRow = statement.executeUpdate();
                if (affectedRow == 0) {
                    throw new SQLException("Creating address failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        addressId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating address failed, no ID obtained.");
                    }
                }
            }
        }
        return addressId;    
    }
}
