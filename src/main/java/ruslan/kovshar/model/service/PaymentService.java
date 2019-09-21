package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.MoneyDao;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.User;

import java.math.BigDecimal;

public class PaymentService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    //TODO: заменить транзакцией(сделать красиво)
    public void makePay(Buyer buyer, User user, BigDecimal money) {
        try (final BuyerDao buyerDao = daoFactory.createBuyerDao();
             final MoneyDao moneyDao = daoFactory.createMoneyDao()) {
            moneyDao.updateUserMoney(user, user.getUserMoney().add(money));
            buyerDao.create(buyer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnMoney(User user, BigDecimal money) {
        try (final MoneyDao moneyDao = daoFactory.createMoneyDao()) {
            moneyDao.updateUserMoney(user, money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
