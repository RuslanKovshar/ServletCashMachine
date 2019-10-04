package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.User;

public class PaymentService {

    private static volatile PaymentService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private PaymentService() {
    }

    /**
     * @return instance
     */
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

    /**
     * saves new buyer and increase user cash
     *
     * @param buyer buyer
     * @param user  user
     */
    public void makePay(Buyer buyer, User user) {
        try (BuyerDao buyerDao = daoFactory.createBuyerDao();
             UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
            buyerDao.create(buyer);
        }
    }

    /**
     * decreases user cash
     *
     * @param user user
     */
    public void returnMoney(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        }
    }

}
