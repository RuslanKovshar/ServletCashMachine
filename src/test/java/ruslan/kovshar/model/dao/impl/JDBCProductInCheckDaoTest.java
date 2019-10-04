package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.entity.*;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

public class JDBCProductInCheckDaoTest {

    private ProductInCheckDao productInCheckDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        productInCheckDao = daoFactory.createProductInCheckDao();
    }

    @After
    public void close() {
        productInCheckDao.close();
    }

    @Test
    public void create() {
        ProductInCheck productInCheck = new ProductInCheck();
        Product product = new CountProduct();
        Check check = new Check();
        check.setId(1L);
        product.setId(1L);
        productInCheck.setProduct(product);
        productInCheck.setCheck(check);
        productInCheck.setValue(1);
        productInCheck.setPrice(BigDecimal.TEN);
        productInCheckDao.create(productInCheck);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
        productInCheckDao.findById(3L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAll() {
        productInCheckDao.findAll();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        productInCheckDao.update(new ProductInCheck());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        productInCheckDao.delete(3L);
    }

    @Test
    public void findAllByCheck() {
        Check check = new Check();
        check.setId(2L);
        Set<ProductInCheck> allProducts = productInCheckDao.findAllByCheck(check);
        assertNotNull(allProducts);
        assertEquals(allProducts.size(), 1);
    }
}