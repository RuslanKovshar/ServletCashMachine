package ruslan.kovshar.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Check {

    private Long id;
    private Set<ProductInCheck> products = new HashSet<>();
    private User user;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductInCheck> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInCheck> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", products=" + products +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
