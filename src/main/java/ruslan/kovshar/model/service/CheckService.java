package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.util.List;

public class CheckService {

    private static volatile CheckService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private CheckService() {
    }

    public static CheckService getInstance() {
        if (instance == null) {
            synchronized (CheckService.class) {
                if (instance == null) {
                    instance = new CheckService();
                }
            }
        }
        return instance;
    }

    public void createCheck(Check check) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final ProductInCheckDao productInCheckDao = daoFactory.createProductInCheckDao()) {
            checkDao.create(check);
            check.getProducts().forEach(productInCheckDao::create);
        }
    }

    public List<Check> getAllUserChecks(User user) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final ProductInCheckDao productInCheckDao = daoFactory.createProductInCheckDao()) {
            List<Check> userChecks = checkDao.findAllByUser(user);
            userChecks.forEach(check -> check.setProducts(productInCheckDao.findAllByCheck(check)));
            return userChecks;
        }
    }

    public void deleteCheck(Check check) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final BuyerDao buyerDao = daoFactory.createBuyerDao()) {
            buyerDao.delete(check.getBuyer().getId());
            checkDao.delete(check.getId());
        }
    }

    public Check findCheckById(long id) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final ProductInCheckDao productInCheckDao = daoFactory.createProductInCheckDao()) {
            Check check = checkDao.findById(id);
            check.setProducts(productInCheckDao.findAllByCheck(check));
            return check;
        }
    }

    public Page<Check> findPageWithChecks(User user, Page pageInfo) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final ProductInCheckDao productInCheckDao = daoFactory.createProductInCheckDao()) {
            Page<Check> page = checkDao.findPageAllByUser(user, pageInfo);
            page.getContent().forEach(check -> check.setProducts(productInCheckDao.findAllByCheck(check)));
            return page;
        }
    }
}
