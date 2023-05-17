package interfaces;

import exception.DataAccessException;

public interface CommunicationDataAccess {
    public boolean isMailAlreadyUsed(String email) throws DataAccessException;

    public void addEmail(int id, String email) throws DataAccessException;

    public void deleteEmail(Integer clientId) throws DataAccessException;
}
