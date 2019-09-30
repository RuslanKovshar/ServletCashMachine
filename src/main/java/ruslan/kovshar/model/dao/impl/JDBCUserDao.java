package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.dao.mapper.RoleMapper;
import ruslan.kovshar.model.dao.mapper.UserMapper;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.exceptions.UserExistException;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
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
            throw new UserExistException();
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
            e.printStackTrace();
        }
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
