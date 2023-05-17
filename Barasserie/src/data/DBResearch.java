package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import tools.DBOutput.User;
import tools.DBOutput.*;

public class DBResearch {
    private String getTopProductQuery(boolean isClient) {
        StringBuilder query = new StringBuilder();

        if (isClient) {
            query.append("SELECT be.firstname, be.lastname, p.name, SUM(td.quantity) AS quantity ")
                .append("FROM business_entity be ");
        } else {
            query.append("SELECT c.name, p.name, SUM(td.quantity) AS quantity ")
                .append("FROM city c ")
                .append("JOIN address a ON c.id = a.city ")
                .append("JOIN business_entity be ON a.id = be.address ");
        }

        query.append("JOIN workflow w ON be.id = w.agent ")
            .append("JOIN document d ON w.id = d.workflow ")
            .append("JOIN transaction_detail td ON d.docType = td.docType AND d.id = td.document ")
            .append("JOIN product p ON td.product = p.id ")
            .append(isClient ? "WHERE be.id = ? " : "WHERE c.id = ? ")
            .append("GROUP BY p.name ")
            .append("ORDER BY SUM(td.quantity) DESC ")
            .append("LIMIT 1");

        return query.toString();
    }

    public TopProductCity getTopProductByCityId(int cityId) throws SQLException {
        TopProductCity topProductCity = null;

        String query = getTopProductQuery(false);
        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cityId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    topProductCity = new TopProductCity(
                        resultSet.getString("c.name"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("quantity")
                    );
                }
            }
        }

        return topProductCity;
    }

    public TopProductClient getTopProductByClientId(int clientId) throws SQLException {
        TopProductClient topProductClient = null;
        
        String query = getTopProductQuery(true);
        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    topProductClient = new TopProductClient(
                        resultSet.getString("be.firstname"),
                        resultSet.getString("be.lastname"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("quantity")
                    );
                }
            }
        }

        return topProductClient;
    }

    private String getUserQuery(boolean isSoloUser) {
        StringBuilder query = new StringBuilder()
            .append("SELECT be.id, be.firstname, be.lastname, com.communicationDetails, ")
            .append("a.street, a.number, c.postalCode, c.name, c.country ")
            .append("FROM business_entity be ")
            .append("JOIN address a ON be.address = a.id ")
            .append("JOIN city c ON a.city = c.id ")
            .append("JOIN communication com ON be.id = com.entity ")
            .append("JOIN communication_type ct ON com.type = ct.type ")
            .append("WHERE ct.type = 'email'");

        if (isSoloUser) {
            query.append(" AND be.id = ?");
        }

        return query.toString();
    }

    public User getUserById(int userId) throws SQLException {
        User user = null;

        String query = getUserQuery(true);
        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                        resultSet.getInt("be.id"),
                        resultSet.getString("be.firstname"),
                        resultSet.getString("be.lastname"),
                        resultSet.getString("com.communicationDetails"),
                        resultSet.getString("a.street"),
                        resultSet.getInt("a.number"),
                        resultSet.getInt("c.postalCode"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.country")
                    );
                }
            }
        }

        return user;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String query = getUserQuery(false);
        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new User(
                        resultSet.getInt("be.id"),
                        resultSet.getString("be.firstname"),
                        resultSet.getString("be.lastname"),
                        resultSet.getString("com.communicationDetails"),
                        resultSet.getString("a.street"),
                        resultSet.getInt("a.number"),
                        resultSet.getInt("c.postalCode"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.country")
                    ));
                }
            }
        }

        return users;
    }

    public List<City> getCities() throws SQLException {
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
}
