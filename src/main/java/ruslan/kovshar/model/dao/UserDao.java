package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
