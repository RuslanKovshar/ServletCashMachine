package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {

    protected Long id;
    protected Integer code;
    protected String nameUA;
    protected String nameEN;
    protected BigDecimal price;
    protected Types type;

    public abstract BigDecimal calculatePrice(Integer value);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(code, product.code) &&
                Objects.equals(nameUA, product.nameUA) &&
                Objects.equals(nameEN, product.nameEN) &&
                Objects.equals(price, product.price) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, nameUA, nameEN, price, type);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code=" + code +
                ", nameUA='" + nameUA + '\'' +
                ", nameEN='" + nameEN + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
