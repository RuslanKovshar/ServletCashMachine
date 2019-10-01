package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

/**
 * Represents an CountProduct Entity
 */
public class CountProduct extends Product{
    @Override
    public BigDecimal calculatePrice(Integer value) {
        return price.multiply(new BigDecimal(value));
    }

    public CountProduct() {
    }

    public CountProduct(Integer code, String name, BigDecimal price, Types type) {
        super(code, name, price, type);
    }
}
