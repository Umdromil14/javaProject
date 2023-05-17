package interfaces;

import java.sql.SQLException;

public interface CommunicationDataAccess {
    public boolean isMailAlreadyUsed(String email) throws SQLException;

    public void addEmail(int id, String email) throws SQLException;

    public void deleteEmail(Integer clientId) throws SQLException;
}
