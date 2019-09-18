package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.entity.Check;

public class CheckService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createCheck(Check check) {
        try(final CheckDao checkDao = daoFactory.createCheckDao();
            final ProductInCheckDao productInCheckDao = daoFactory.createProductInCheckDao()) {
            checkDao.create(check);
            check.getProducts().forEach(productInCheckDao::create);
        }
    }
}
