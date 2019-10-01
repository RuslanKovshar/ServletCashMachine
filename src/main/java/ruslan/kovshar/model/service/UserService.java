package ruslan.kovshar.model.service;

import ruslan.kovshar.controller.dto.UserDTO;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.exceptions.UserExistException;

import java.util.Optional;

public class UserService {

    private static UserService instance;
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
            e.printStackTrace();
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
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
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
                .build();
    }
}
