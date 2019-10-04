package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.exceptions.ProductExistException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCProductDaoTest {

    private ProductDao productDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        productDao = daoFactory.createProductDao();
    }

    @After
    public void close() {
        productDao.close();
    }

    @Test
    public void create() {
        Product product = new CountProduct(1337, "newProd", BigDecimal.TEN, Types.PIECE_PRODUCT);
        productDao.create(product);
        assertNotNull(product.getId());
    }

    @Test(expected = ProductExistException.class)
    public void creationFail() {
        Product product = new CountProduct(222, "newProd", BigDecimal.TEN, Types.PIECE_PRODUCT);
        productDao.create(product);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
        productDao.findById(1L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAll() {
        productDao.findAll();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        productDao.update(new WeightProduct());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        productDao.delete(1L);
    }

    @Test
    public void findByName() {
        Optional<Product> product = productDao.findByName("Coca-cola");
        assertTrue(product.isPresent());
    }

    @Test
    public void findByCode() {
        Optional<Product> product = productDao.findByCode(222);
        assertTrue(product.isPresent());
    }
}