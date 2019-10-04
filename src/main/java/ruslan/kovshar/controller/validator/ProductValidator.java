package ruslan.kovshar.controller.validator;

import ruslan.kovshar.controller.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static ruslan.kovshar.textconstants.ValidationMessages.*;

/**
 * validates product info
 */
public class ProductValidator implements Validator<ProductDTO> {

    private Map<String, String> errors = new HashMap<>();

    @Override
    public void validate(ProductDTO entity) {
        if (entity.getCode() == null) {
            errors.put(CODE_ERROR, CODE_MESSAGE);
        }
        if (entity.getName() == null || entity.getName().equals("")) {
            errors.put(NAME_ERROR, NAME_MESSAGE);
        }
        if (entity.getPrice().equals(BigDecimal.ZERO) || entity.getPrice().doubleValue() < 0.1) {
            errors.put(PRICE_ERROR, PRICE_MESSAGE);
        }
        if (entity.getCountOfProduct() == null || entity.getCountOfProduct() <= 0) {
            errors.put(COUNT_OF_PRODUCT_ERROR, COUNT_OF_PRODUCT_MESSAGE);
        }
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public Map<String, String> getErrors() {
        return errors;
    }
}
