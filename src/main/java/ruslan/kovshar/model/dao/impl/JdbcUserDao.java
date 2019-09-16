package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.dao.mapper.UserMapper;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class JdbcUserDao implements UserDao {
    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareCall(SQL.SELECT_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                result = Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
