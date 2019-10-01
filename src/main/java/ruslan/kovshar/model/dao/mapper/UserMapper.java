package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * extracts user from result set
 */
public class UserMapper extends Mapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User.Builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .firstNameEN(rs.getString("first_name_en"))
                .firstNameUA(rs.getString("first_name_ua"))
                .secondNameEN(rs.getString("second_name_en"))
                .secondNameUA(rs.getString("second_name_ua"))
                .authorities(new HashSet<>())
                .userCash(rs.getBigDecimal("cash"))
                .checks(new HashSet<>())
                .build();
        return makeUnique(user, user.getId());
    }
}
