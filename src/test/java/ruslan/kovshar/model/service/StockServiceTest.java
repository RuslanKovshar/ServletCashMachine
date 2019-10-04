package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.exceptions.NotEnoughProductException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StockServiceTest {

    private StockService stockService;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        stockService = StockService.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(StockService.getInstance());
    }

    @Test
    public void addProductToStock() {
        Product product = new CountProduct(256, "Cacao", BigDecimal.valueOf(26.99), Types.PIECE_PRODUCT);
        product.setId(1L);
        stockService.addProductToStock(new Stock(product,5));
    }

    @Test
    public void updateStock() throws NotEnoughProductException {
        Product product = new CountProduct();
        product.setId(1L);
        stockService.updateStock(product, -1);
    }
}