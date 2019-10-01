package ruslan.kovshar.controller.dto;

import ruslan.kovshar.model.enums.Types;

import java.math.BigDecimal;

/**
 * contains product information that executes from request
 */
public class ProductDTO {

    private Integer code;
    private String name;
    private BigDecimal price;
    private Types type;
    private Integer countOfProduct;

    public ProductDTO(Integer code, String name, BigDecimal price, Types type, Integer countOfProduct) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.countOfProduct = countOfProduct;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Integer countOfProduct) {
        this.countOfProduct = countOfProduct;
    }
}
