package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.textconstants.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * serves to access role in database
 */
public class JDBCRoleDao implements RoleDao {

    private static final Logger log = Logger.getLogger(JDBCRoleDao.class);

    private Connection connection;

    JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setUserRole(User user, Roles role) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_ROLE)) {
            ps.setLong(1, user.getId());
            ps.setString(2, role.name());
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void create(Roles entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Roles findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Roles> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Roles entity) {
        throw new UnsupportedOperationException();
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
