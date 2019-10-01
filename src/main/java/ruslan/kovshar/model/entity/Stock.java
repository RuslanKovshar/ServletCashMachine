package ruslan.kovshar.model.entity;

/**
 * Represents an Stock Entity
 */
public class Stock {
    private Long id;
    private Product product;
    private Integer countOfProduct;

    public Stock() {
    }

    public Stock(Product product, Integer countOfProduct) {
        this.product = product;
        this.countOfProduct = countOfProduct;
    }

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

    public Integer getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Integer countOfProduct) {
        this.countOfProduct = countOfProduct;
    }
}
