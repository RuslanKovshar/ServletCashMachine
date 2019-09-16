package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

public class CountProduct extends Product{
    @Override
    public BigDecimal calculatePrice(Number value) {
        return price.multiply(new BigDecimal(value.intValue()));
    }

    public CountProduct() {
    }

    public CountProduct(Integer code, String nameUA, String nameEN, BigDecimal price, Types type) {
        super(code, nameUA, nameEN, price, type);
    }
}
