package main.business;

import java.util.List;

import main.exception.DataAccessException;
import main.data.DocStatusDBAccess;
import main.interfaces.DocStatusDataAccess;

public class DocStatusManager {
    private DocStatusDataAccess dao;

    public DocStatusManager() {
        this.dao = new DocStatusDBAccess();
    }

    public List<String> getAllDocStatus() throws DataAccessException {
        return dao.getAllDocStatus();
    }
}