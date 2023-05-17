package main.data;

import java.sql.*;

import main.exception.DataAccessException;
import main.interfaces.CommunicationDataAccess;

public class CommunicationDBAccess implements CommunicationDataAccess {
    Connection connection;

    @Override
    public boolean isMailAlreadyUsed(String email) throws DataAccessException {
        String query = "SELECT communicationDetails FROM communication WHERE communicationDetails = ?";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            throw new DataAccessException("error while accessing the email");
        }

    }

    @Override
    public void addEmail(int id, String email) throws DataAccessException {
        String query = "INSERT INTO communication (entity, type, communicationDetails) VALUES (?, ?, ?)";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, "email");
            statement.setString(3, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("error while adding the email");
        }
    }

    @Override
    public void deleteEmail(Integer clientId) throws DataAccessException {
        connection = SingletonConnection.getInstance();

        String query = "DElETE FROM communication WHERE entity = ? AND type = 'email'";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("error while deleting the email");
        }
    }
}