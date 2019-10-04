package ruslan.kovshar.model.service;

import org.apache.log4j.Logger;
import ruslan.kovshar.controller.dto.UserDTO;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.dao.impl.JDBCUserDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.exceptions.UserExistException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    private static volatile UserService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private UserService() {
    }

    /**
     * @return instance
     */
    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    /**
     * saves new user
     *
     * @param user user
     * @param role role
     * @return true if user created, false if not
     */
    public boolean addUser(User user, Roles role) {
        try (UserDao userDao = daoFactory.createUserDao();
             RoleDao roleDao = daoFactory.createRoleDao()) {
            userDao.create(user);
            roleDao.setUserRole(user, role);
            return true;
        } catch (UserExistException e) {
            log.error(e);
        }
        return false;
    }

    /**
     * finds user by password and email
     *
     * @param email    user email
     * @param password user password
     * @return user
     */
    public Optional<User> getUser(String email, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByEmailAndPassword(email, password);
        }
    }


    /**
     * updates user
     *
     * @param user user
     */
    public void updateUser(User user) {
        try (UserDao userDao = daoFactory.createUserDao();
             RoleDao roleDao = daoFactory.createRoleDao()) {
            roleDao.deleteUserRole(user);
            Set<Roles> authorities = user.getAuthorities();
            authorities.forEach(roles -> roleDao.setUserRole(user, roles));
            userDao.update(user);
        }
    }

    /**
     * finds all users
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    /**
     * finds user by id
     *
     * @param id user id
     * @return user
     */
    public User getUserById(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }

    /**
     * creates user from userDTO
     *
     * @param userDTO user information
     * @return user
     */
    public User createUser(UserDTO userDTO) {
        return new User.Builder()
                .email(userDTO.getEmail())
                .password(Encoder.encodePassword(userDTO.getPassword()))
                .firstNameEN(userDTO.getFirstNameEN())
                .firstNameUA(userDTO.getFirstNameUA())
                .secondNameEN(userDTO.getSecondNameEN())
                .secondNameUA(userDTO.getSecondNameUA())
                .userCash(BigDecimal.ZERO)
                .build();
    }
}
