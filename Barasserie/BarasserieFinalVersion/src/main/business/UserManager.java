package main.business;

import java.util.List;

import main.exception.DataAccessException;
import main.data.UserDBAccess;
import main.exception.UserRestrictedException;
import main.model.TopProductClient;
import main.model.User;
import main.tools.Utils;

public class UserManager {
    private UserDBAccess userDao;

    public UserManager() {
        userDao = new UserDBAccess();
    }

    public User getUser(int userId) throws DataAccessException {
        return userDao.getUser(userId);
    }

    public List<User> getAllUsers() throws DataAccessException {
        return userDao.getAllUsers();
    }

    public void createUser(User user) throws DataAccessException {
        user.setSalt(Utils.generateSalt());
        user.setPassword(Utils.hashPassword(user.getPassword(), user.getSalt()));
        userDao.create(user);
    }

    public void updateUser(User user) throws DataAccessException {
        user.setPassword(Utils.hashPassword(user.getPassword(), user.getSalt()));
        userDao.update(user);
    }

    public void deleteUser(User user) throws DataAccessException, UserRestrictedException {
        userDao.delete(user);   
    }

    public TopProductClient getTopProduct(int userId) throws DataAccessException {
        return userDao.getTopProduct(userId);
    }
}