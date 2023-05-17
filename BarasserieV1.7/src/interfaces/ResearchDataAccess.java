package interfaces;

import exception.DataAccessException;

import model.TopProductCity;
import model.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws DataAccessException;

    public TopProductClient getTopProductByClient(int clientId) throws DataAccessException;
}
