package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;

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
            while (generatedKeys.next()){
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

    }

    @Override
    public void close() {

    }
}
