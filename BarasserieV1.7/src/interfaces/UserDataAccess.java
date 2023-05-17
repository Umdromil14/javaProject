package interfaces;

import exception.DataAccessException;
import java.util.List;

import exception.UserRestrictedException;
import model.TopProductClient;
import model.User;

public interface UserDataAccess {
    public TopProductClient getTopProduct(int userId) throws DataAccessException;

    public User getUser(int userId) throws DataAccessException;

    public List<User> getAllUsers() throws DataAccessException;

    public void create(User user) throws DataAccessException;
    public void update(User user) throws DataAccessException;
    public void delete(User user) throws DataAccessException, UserRestrictedException;

    public boolean userHasDocument(Integer idUser) throws DataAccessException ;
}
