package business;

import java.sql.SQLException;
import java.util.List;

import data.DocStatusDBAccess;
import interfaces.DocStatusDataAccess;

public class DocStatusManager {
    private DocStatusDataAccess dao;

    public DocStatusManager() {
        this.dao = new DocStatusDBAccess();
    }

    public List<String> getAllDocStatus() throws SQLException {
        return dao.getAllDocStatus();
    }
}