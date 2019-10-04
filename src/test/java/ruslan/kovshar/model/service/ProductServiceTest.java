package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ruslan.kovshar.controller.dto.ProductDTO;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.exceptions.ProductExistException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    private Product product;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new WeightProduct();
    }

    @Test
    public void findProductByCode() {
        Integer code = 222;
        Mockito.when(productService.findProductByCode(code))
                .thenReturn(product);

        assertNotNull(productService.findProductByCode(code));
        assertEquals(productService.findProductByCode(code),product);
    }

    @Test
    public void findProductByName() {
        String name = "Coca-cola";
        Mockito.when(productService.findProductByName(name))
                .thenReturn(product);

        assertNotNull(productService.findProductByName(name));
        assertEquals(productService.findProductByName(name),product);
    }

    @Test
    public void createProductFailed() {
        Mockito.doThrow(ProductExistException.class).when(productDao).create(product);
        assertFalse(productService.createProduct(product));
    }
}