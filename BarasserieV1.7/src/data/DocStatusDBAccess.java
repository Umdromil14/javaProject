package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.DataAccessException;
import interfaces.DocStatusDataAccess;


public class DocStatusDBAccess implements DocStatusDataAccess {
    private Connection connection;
    
    @Override
    public List<String> getAllDocStatus() throws DataAccessException {
        List<String> docStatus = new ArrayList<>();

        String query = "SELECT description FROM doc_status";
        try {
            connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    docStatus.add(resultSet.getString("description"));
                }
            }
        }
        } catch (SQLException e) {
            throw new DataAccessException("error while loading the document status");
        }
        
        return docStatus;
    }
}
