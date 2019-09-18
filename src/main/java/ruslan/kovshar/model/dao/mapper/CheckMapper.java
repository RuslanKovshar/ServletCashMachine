package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckMapper extends Mapper<Check> {
    @Override
    public Check extractFromResultSet(ResultSet rs) throws SQLException {
        Check check = new Check();
        check.setId(rs.getLong("id"));
        check.setTotalPrice(rs.getBigDecimal("total_price"));
        return makeUnique(check, check.getId());
    }
}
