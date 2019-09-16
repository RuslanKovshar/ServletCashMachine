package ruslan.kovshar.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Check {

    private Long id;
    private Set<ProductInCheck> products;
    private User user;
    private BigDecimal totalPrice;
}
