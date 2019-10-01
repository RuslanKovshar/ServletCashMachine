package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.enums.Roles;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * extracts role from result set
 */
public class RoleMapper extends Mapper<Roles> {
    @Override
    public Roles extractFromResultSet(ResultSet rs) throws SQLException {
        return Roles.valueOf(rs.getString("authorities"));
    }
}
