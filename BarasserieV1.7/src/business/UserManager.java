package business;
import java.sql.SQLException;
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

    public User getUser(int userId) throws SQLException {
        return userDao.getUser(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void createUser(User user) throws SQLException {
        user.setSalt(Utils.generateSalt());
        user.setPassword(Utils.hashPassword(user.getPassword(), user.getSalt()));
        userDao.create(user);
    }

    public void updateUser(User user) throws SQLException {
        user.setPassword(Utils.hashPassword(user.getPassword(), user.getSalt()));
        userDao.update(user);
    }

    public void deleteUser(User user) throws SQLException, UserRestrictedException {
        userDao.delete(user);   
    }

    public TopProductClient getTopProduct(int userId) throws SQLException {
        return userDao.getTopProduct(userId);
    }
}