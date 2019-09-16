package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

public abstract class Product {

    protected Long id;
    protected Integer code;
    protected String nameUA;
    protected String nameEN;
    protected BigDecimal price;
    protected Types type;

    public abstract BigDecimal calculatePrice(Number value);

    public Product() {
    }

    public Product(Integer code, String nameUA, String nameEN, BigDecimal price, Types type) {
        this.code = code;
        this.nameUA = nameUA;
        this.nameEN = nameEN;
        this.price = price;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
