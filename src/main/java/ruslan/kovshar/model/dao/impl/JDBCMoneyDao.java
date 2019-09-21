package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.MoneyDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.view.SQL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCMoneyDao implements MoneyDao {

    private Connection connection;

    public JDBCMoneyDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserMoney(User user, BigDecimal value) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_AMOUNT)) {
            ps.setLong(1, user.getId());
            ps.setBigDecimal(2, value);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserMoney(User user, BigDecimal newValue) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.UPDATE_AMOUNT_OF_MONEY)) {
            ps.setBigDecimal(1, newValue);
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
