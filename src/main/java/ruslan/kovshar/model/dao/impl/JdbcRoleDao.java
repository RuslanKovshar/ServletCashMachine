package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.dao.mapper.RoleMapper;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.view.SQL;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JdbcRoleDao implements RoleDao {
    private Connection connection;

    public JdbcRoleDao(Connection connection) {
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
    public Set<Roles> findByUserId(User user) {
        Set<Roles> result = new HashSet<>();
        try (final PreparedStatement ps = connection.prepareStatement(SQL.SELECT_ROLE)) {
            ps.setLong(1,user.getId());
            final ResultSet resultSet = ps.executeQuery();
            RoleMapper roleMapper = new RoleMapper();
            while (resultSet.next()) {
                result.add(roleMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
