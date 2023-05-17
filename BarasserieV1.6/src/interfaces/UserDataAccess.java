package interfaces;

import java.sql.SQLException;
import java.util.List;

import tools.DBOutput.TopProductClient;
import tools.DBOutput.User;

public interface UserDataAccess {
    public TopProductClient getTopProduct(int userId) throws SQLException;

    public User getUser(int userId) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public void create(User bussinessEntity) throws SQLException;
    public void update(User bussinessEntity) throws SQLException;
    public void delete(User user) throws SQLException;

    //TODO: add other methods
}