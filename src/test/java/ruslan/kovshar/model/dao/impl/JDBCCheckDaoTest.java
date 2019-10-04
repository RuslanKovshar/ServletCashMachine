package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.dao.CheckDao;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.util.List;

import static org.junit.Assert.*;

public class JDBCCheckDaoTest {

    private CheckDao checkDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        checkDao = daoFactory.createCheckDao();
    }

    @After
    public void close() {
        checkDao.close();
    }

    @Test
    public void create() {
        Check check = new Check();
        Buyer buyer = new Buyer();
        buyer.setId(1L);
        User user = new User.Builder().id(1L).build();
        check.setBuyer(buyer);
        check.setUser(user);
        checkDao.create(check);
        assertNotNull(check.getId());
    }

    @Test
    public void findById() {
        Check check = checkDao.findById(2L);
        assertNotNull(check);
    }

    @Test
    public void findAll() {
        List<Check> allChecks = checkDao.findAll();
        assertNotNull(allChecks);
        assertTrue(allChecks.size() > 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        Check check = new Check();
        checkDao.update(check);
    }

    @Test
    public void delete() {
        checkDao.delete(1L);
    }

    @Test
    public void findAllByUser() {
        List<Check> allChecksByUser = checkDao.findAllByUser(new User.Builder().id(2L).build());
        assertNotNull(allChecksByUser);
        assertEquals(allChecksByUser.size(), 1);
    }

    @Test
    public void findPageAllByUser() {
        Page pageInfo = new Page(1, 1, "ASC");
        Page<Check> page = checkDao.findPageAllByUser(new User.Builder().id(2L).build(), pageInfo);
        assertNotNull(page);
        assertEquals(page.getContent().size(), pageInfo.getMaxResult());
    }
}