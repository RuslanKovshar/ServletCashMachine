package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.controller.dto.ProductDTO;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        productService = ProductService.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(ProductService.getInstance());
    }

    @Test
    public void createProduct() {
        Product product = new CountProduct(256, "Cacao", BigDecimal.valueOf(26.99), Types.PIECE_PRODUCT);
        product.setId(1L);
        productService.createProduct(product);
    }

    @Test
    public void findProductByCode() {
        Product productByCode = productService.findProductByCode(222);
        assertNotNull(productByCode);
    }

    @Test
    public void findProductByName() {
        Product productByName = productService.findProductByName("Coca-cola");
        assertNotNull(productByName);
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO(1337, "newProd", BigDecimal.TEN, Types.PIECE_PRODUCT, 5);
        Product product = productService.createProduct(productDTO);
        assertNotNull(product);
        assertEquals(productDTO.getCode(), product.getCode());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getType(), product.getType());
    }
}