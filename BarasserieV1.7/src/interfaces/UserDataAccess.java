package interfaces;

import java.sql.SQLException;
import java.util.List;

import exception.UserRestrictedException;
import model.TopProductClient;
import model.User;

public interface UserDataAccess {
    public TopProductClient getTopProduct(int userId) throws SQLException;

    public User getUser(int userId) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public void create(User user) throws SQLException;
    public void update(User user) throws SQLException;
    public void delete(User user) throws SQLException, UserRestrictedException;

    public boolean userHasDocument(Integer idUser) throws SQLException ;
}
