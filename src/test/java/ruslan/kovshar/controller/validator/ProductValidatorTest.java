package ruslan.kovshar.controller.validator;

import org.junit.Before;
import org.junit.Test;
import ruslan.kovshar.controller.dto.ProductDTO;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class ProductValidatorTest {

    private Validator<ProductDTO> validator;

    @Before
    public void setUp() {
        validator = new ProductValidator();
    }

    @Test
    public void validate() {
        validator.validate(new ProductDTO(222,"New Product", BigDecimal.TEN, Types.PRODUCT_BY_WEIGHT,5000));
        assertFalse(validator.hasErrors());
    }

    @Test
    public void validateError() {
        validator.validate(new ProductDTO(222,"", BigDecimal.ZERO, Types.PRODUCT_BY_WEIGHT,5000));
        assertTrue(validator.hasErrors());
    }

    @Test
    public void hasErrors() {
        boolean hasErrors = validator.hasErrors();
        assertFalse(hasErrors);
    }

    @Test
    public void getErrors() {
        Map<String, String> errors = validator.getErrors();
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
    }
}