package interfaces;

import exception.DataAccessException;
import java.util.List;

public interface DocStatusDataAccess {
    public List<String> getAllDocStatus() throws DataAccessException;
}
