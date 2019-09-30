package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCRoleDao implements RoleDao {
    private Connection connection;

    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setUserRole(User user, Roles role) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_ROLE)) {
            ps.setLong(1, user.getId());
            ps.setString(2, role.name());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Roles entity) {

    }

    @Override
    public Roles findById(Long id) {
        return null;
    }

    @Override
    public List<Roles> findAll() {
        return null;
    }

    @Override
    public void update(Roles entity) {

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
