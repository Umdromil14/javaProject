package interfaces;

import java.sql.SQLException;

import tools.DBOutput.TopProductCity;
import tools.DBOutput.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws SQLException;

    public TopProductClient getTopProductByClient(int clientId) throws SQLException;
}
