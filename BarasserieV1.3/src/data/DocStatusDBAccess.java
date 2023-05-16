<<<<<<< HEAD
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.DocStatusDataAccess;


public class DocStatusDBAccess implements DocStatusDataAccess {
    private Connection connection;
    public List<String> getAllDocStatus() throws SQLException {
        List<String> docStatus = new ArrayList<>();

        String query = "SELECT description FROM doc_status";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    docStatus.add(resultSet.getString("description"));
                }
            }
        }

        return docStatus;
    }
}
=======
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.DocStatusDataAccess;


public class DocStatusDBAccess implements DocStatusDataAccess {
    private Connection connection;
    public List<String> getAllDocStatus() throws SQLException {
        List<String> docStatus = new ArrayList<>();

        String query = "SELECT description FROM doc_status";

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    docStatus.add(resultSet.getString("description"));
                }
            }
        }

        return docStatus;
    }
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
