package business;

import java.sql.SQLException;

import data.CommunicationDBAccess;
import interfaces.CommunicationDataAccess;

public class CommunicationManager {
    private CommunicationDataAccess dao;

    public CommunicationManager() {
        dao = new CommunicationDBAccess();
    }

    public boolean isMailAlreadyUsed(String email) throws SQLException {
        return dao.isMailAlreadyUsed(email);
    }
    
    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
}