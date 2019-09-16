package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends Mapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstNameUA(rs.getString("first_name_ua"));
        user.setFirstNameEN(rs.getString("first_name_en"));
        user.setSecondNameUA(rs.getString("second_name_ua"));
        user.setSecondNameEN(rs.getString("second_name_en"));
        return makeUnique(user, user.getId());
    }
}
