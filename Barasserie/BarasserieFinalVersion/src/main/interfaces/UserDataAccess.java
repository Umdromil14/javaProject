package main.interfaces;

import java.util.List;

import main.exception.*;
import main.model.TopProductClient;
import main.model.User;

public interface UserDataAccess {
    public TopProductClient getTopProduct(int userId) throws DataAccessException;

    public User getUser(int userId) throws DataAccessException;

    public List<User> getAllUsers() throws DataAccessException;

    public void create(User user) throws DataAccessException;
    public void update(User user) throws DataAccessException;
    public void delete(User user) throws DataAccessException, UserRestrictedException;

    public boolean userHasDocument(Integer idUser) throws DataAccessException ;
}
