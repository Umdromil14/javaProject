<<<<<<< HEAD
package interfaces;

import java.sql.SQLException;

import tools.DBOutput.TopProductCity;
import tools.DBOutput.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws SQLException;

    public TopProductClient getTopProductByClient(int clientId) throws SQLException;
}
=======
package interfaces;

import java.sql.SQLException;

import tools.DBOutput.TopProductCity;
import tools.DBOutput.TopProductClient;

public interface ResearchDataAccess {
    public TopProductCity getTopProductByCity(int cityId) throws SQLException;

    public TopProductClient getTopProductByClient(int clientId) throws SQLException;
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
