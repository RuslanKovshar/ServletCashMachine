package ruslan.kovshar.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Check {

    private Long id;
    private Set<ProductInCheck> products = new HashSet<>();
    private User user;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private Buyer buyer;

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

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void calculateTotalPrice() {
        double sum = products.stream().mapToDouble(products -> products.getPrice().doubleValue()).sum();
        this.totalPrice = BigDecimal.valueOf(sum);
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
