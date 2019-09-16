package ruslan.kovshar.model.dto;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer code;
    private String nameUA;
    private String nameEN;
    private BigDecimal price;
    private Integer count;
    private Types type;

    public ProductDTO() {
    }

    public ProductDTO(Integer code, String nameUA, String nameEN, BigDecimal price, Integer count, Types type) {
        this.code = code;
        this.nameUA = nameUA;
        this.nameEN = nameEN;
        this.price = price;
        this.count = count;
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
