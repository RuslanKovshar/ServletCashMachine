package ruslan.kovshar.model.entity;

import java.math.BigDecimal;

/**
 * Represents an ProductInCheck Entity
 */
public class ProductInCheck {
    private Long id;
    private Product product;
    private Integer value;
    private BigDecimal price = BigDecimal.ZERO;
    private Check check;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
