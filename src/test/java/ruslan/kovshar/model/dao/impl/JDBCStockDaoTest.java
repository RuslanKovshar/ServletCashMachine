package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.StockDao;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCStockDaoTest {

    private StockDao stockDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        stockDao = daoFactory.createStockDao();
    }

    @After
    public void close() {
        stockDao.close();
    }

    @Test
    public void create() {
        Product product = new CountProduct(256, "Cacao", BigDecimal.valueOf(26.99), Types.PIECE_PRODUCT);
        product.setId(1L);
        stockDao.create(new Stock(product,5));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
        stockDao.findById(1L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAll() {
        stockDao.findAll();
    }

    @Test
    public void update() {
        Product product = new CountProduct(256, "Cacao", BigDecimal.valueOf(26.99), Types.PIECE_PRODUCT);
        product.setId(1L);
        Stock stock = new Stock(product,5);
        stock.setId(1L);
        stockDao.update(stock);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        stockDao.delete(1L);
    }

    @Test
    public void findByProduct() {
        Product product = new CountProduct();
        product.setId(1L);
        Optional<Stock> stock = stockDao.findByProduct(product);
        assertTrue(stock.isPresent());
    }
}