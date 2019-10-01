package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an WeightProduct Entity
 */
public class WeightProduct extends Product {
    @Override
    public BigDecimal calculatePrice(Integer value) {
        return price.divide(new BigDecimal(1000 / value),2, RoundingMode.HALF_UP);
    }

    public WeightProduct() {
    }

    public WeightProduct(Integer code, String name, BigDecimal price, Types type) {
        super(code, name, price, type);
    }
}
