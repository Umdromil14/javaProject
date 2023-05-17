package interfaces;

import java.sql.SQLException;

public interface CommunicationDataAccess {
    public boolean isMailAlreadyUsed(String email) throws SQLException;
}
