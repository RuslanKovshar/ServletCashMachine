package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.User;

import java.math.BigDecimal;

public interface MoneyDao extends AutoCloseable {
    void setUserMoney(User user, BigDecimal value);

    void updateUserMoney(User user, BigDecimal newValue);
}
