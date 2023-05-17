package main.interfaces;

import main.exception.DataAccessException;

import main.model.TopProductCity;
import main.model.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws DataAccessException;

    public TopProductClient getTopProductByClient(int clientId) throws DataAccessException;
}
