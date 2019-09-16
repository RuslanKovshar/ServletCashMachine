package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.entity.Check;

import java.sql.Connection;
import java.util.List;

public class JdbcCheckDao implements CheckDao {
    private Connection connection;

    public JdbcCheckDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Check entity) {

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
