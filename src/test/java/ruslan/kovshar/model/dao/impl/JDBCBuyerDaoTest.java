package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.entity.Buyer;

import static org.junit.Assert.assertNotNull;

public class JDBCBuyerDaoTest {

    private BuyerDao buyerDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        buyerDao = daoFactory.createBuyerDao();
    }

    @After
    public void close() {
        buyerDao.close();
    }

    @Test
    public void create() {
        Buyer buyer = new Buyer("New Buyer","1111222233334444");
        buyerDao.create(buyer);
        assertNotNull(buyer.getId());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
        buyerDao.findById(1L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAll() {
        buyerDao.findAll();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        buyerDao.update(new Buyer());
    }

    @Test
    public void delete() {
        buyerDao.delete(1L);
    }
}