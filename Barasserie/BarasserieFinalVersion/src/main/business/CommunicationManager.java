package main.business;

import main.exception.DataAccessException;
import main.data.CommunicationDBAccess;
import main.interfaces.CommunicationDataAccess;

public class CommunicationManager {
    private CommunicationDataAccess dao;

    public CommunicationManager() {
        dao = new CommunicationDBAccess();
    }

    public boolean isMailAlreadyUsed(String email) throws DataAccessException {
        return dao.isMailAlreadyUsed(email);
    }
    
    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
}