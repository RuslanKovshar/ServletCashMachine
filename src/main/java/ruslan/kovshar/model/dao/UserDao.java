package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.User;

import java.util.Optional;

/**
 * User DAO
 */
public interface UserDao extends GenericDao<User> {
    /**
     * finds user by email and password
     *
     * @param email    user email
     * @param password user password
     * @return user
     */
    Optional<User> findByEmailAndPassword(String email, String password);
}
