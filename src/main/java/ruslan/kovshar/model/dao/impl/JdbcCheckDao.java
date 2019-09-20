package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.dao.mapper.CheckMapper;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCheckDao implements CheckDao {
    private Connection connection;

    public JdbcCheckDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Check entity) {
        try (PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_CHECK, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBigDecimal(1, entity.getTotalPrice());
            ps.setLong(2, entity.getUser().getId());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Check findById(Long id) {
        return null;
    }

    @Override
    public List<Check> findAll() {
        return null;
    }

    @Override
    public void update(Check entity) {

    }

    @Override
    public void delete(Long id) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.DELETE_CHECK)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Check> findAllByUser(User user) {
        List<Check> checks = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL.SELECT_CHECK_BY_USER)) {
            ps.setLong(1, user.getId());
            final ResultSet resultSet = ps.executeQuery();
            CheckMapper mapper = new CheckMapper();
            while (resultSet.next()) {
                Check check = mapper.extractFromResultSet(resultSet);
                check.setUser(user);
                checks.add(check);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checks;
    }
}
