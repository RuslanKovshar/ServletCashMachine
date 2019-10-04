package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.dao.mapper.RoleMapper;
import ruslan.kovshar.model.dao.mapper.UserMapper;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.exceptions.UserExistException;
import ruslan.kovshar.textconstants.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * serves to access user in database
 */
public class JDBCUserDao implements UserDao {

    private static final Logger log = Logger.getLogger(JDBCUserDao.class);

    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareCall(SQL.SELECT_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();

            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                if (user != null) {
                    user.getAuthorities().add(roleMapper.extractFromResultSet(resultSet));
                }
            }
            result = Optional.ofNullable(user);
        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL.INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstNameUA());
            preparedStatement.setString(4, entity.getSecondNameUA());
            preparedStatement.setString(5, entity.getFirstNameEN());
            preparedStatement.setString(6, entity.getSecondNameEN());
            preparedStatement.setBigDecimal(7, entity.getUserCash());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new UserExistException();
        }
    }

    @Override
    public User findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL.UPDATE_USER)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstNameUA());
            preparedStatement.setString(4, entity.getSecondNameUA());
            preparedStatement.setString(5, entity.getFirstNameEN());
            preparedStatement.setString(6, entity.getSecondNameEN());
            preparedStatement.setBigDecimal(7, entity.getUserCash());
            preparedStatement.setLong(8, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
