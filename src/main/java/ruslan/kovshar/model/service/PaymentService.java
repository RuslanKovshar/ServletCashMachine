package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.User;

public class PaymentService {

    private static PaymentService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new PaymentService();
                }
            }
        }
        return instance;
    }

    //TODO: заменить транзакцией(сделать красиво)
    public void makePay(Buyer buyer, User user) {
        try (final BuyerDao buyerDao = daoFactory.createBuyerDao();
             final UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
            buyerDao.create(buyer);
        }
    }

    //TODO:
    public void returnMoney(User user) {
        try (final UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        }
    }

}
