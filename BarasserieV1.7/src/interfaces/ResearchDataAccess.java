package interfaces;

import java.sql.SQLException;

import model.TopProductCity;
import model.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws SQLException;

    public TopProductClient getTopProductByClient(int clientId) throws SQLException;
}
