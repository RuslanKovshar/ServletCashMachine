package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.enums.Types;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper extends Mapper<Roles> {
    @Override
    public Roles extractFromResultSet(ResultSet rs) throws SQLException {
        return Roles.valueOf(rs.getString("authorities"));
    }
}
