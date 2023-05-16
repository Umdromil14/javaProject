<<<<<<< HEAD
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

=======
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

>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
