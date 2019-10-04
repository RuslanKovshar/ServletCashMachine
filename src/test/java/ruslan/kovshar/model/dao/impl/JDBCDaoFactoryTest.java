package ruslan.kovshar.model.dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;

import static org.junit.Assert.*;

public class JDBCDaoFactoryTest {

    private DaoFactory daoFactory;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void instance() {
        assertNotNull(DaoFactory.getInstance());
    }

    @Test
    public void createCheckDao() {
        assertNotNull(daoFactory.createCheckDao());
    }

    @Test
    public void createProductDao() {
        assertNotNull(daoFactory.createProductDao());
    }

    @Test
    public void createRoleDao() {
        assertNotNull(daoFactory.createRoleDao());
    }

    @Test
    public void createStockDao() {
        assertNotNull(daoFactory.createStockDao());
    }

    @Test
    public void createUserDao() {
        assertNotNull(daoFactory.createUserDao());
    }

    @Test
    public void createProductInCheckDao() {
        assertNotNull(daoFactory.createProductInCheckDao());
    }

    @Test
    public void createBuyerDao() {
        assertNotNull(daoFactory.createBuyerDao());
    }
}