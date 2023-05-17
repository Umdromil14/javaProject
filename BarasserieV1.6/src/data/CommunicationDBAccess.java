package data;

import java.sql.*;

import interfaces.CommunicationDataAccess;

public class CommunicationDBAccess implements CommunicationDataAccess {
    Connection connection;

    @Override
    public boolean isMailAlreadyUsed(String email) throws SQLException {
        String query = "SELECT communicationDetails FROM communication WHERE communicationDetails = ?";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            try(ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    @Override
    public void addEmail(int id, String email) throws SQLException {
        String query = "INSERT INTO communication (entity, type, communicationDetails) VALUES (?, ?, ?)";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, "email");
            statement.setString(3, email);

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteEmail(Integer clientId) throws SQLException {
        connection = SingletonConnection.getInstance();

        String query = new StringBuilder()
            .append("DELETE FROM communication ")
            .append("WHERE entity = ?")
            .toString();
            
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }
}