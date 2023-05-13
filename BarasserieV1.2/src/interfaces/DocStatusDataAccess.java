package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DocStatusDataAccess {
    public List<String> getAllDocStatus() throws SQLException;
}
