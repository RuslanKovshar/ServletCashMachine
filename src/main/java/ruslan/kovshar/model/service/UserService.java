package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import java.util.Optional;
import java.util.Set;

public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void addUser(User user, Roles role) {
        try (UserDao userDao = daoFactory.createUserDao();
             RoleDao roleDao = daoFactory.createRoleDao()) {
            userDao.create(user);
            roleDao.setUserRole(user, role);
        }
    }

    public Optional<User> getUser(String email, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByEmailAndPassword(email, password);
        }
    }
}
