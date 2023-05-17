package business;

import exception.DataAccessException;
import java.util.List;

import data.UserDBAccess;
import exception.UserRestrictedException;
import model.TopProductClient;
import model.User;
import tools.Utils;

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