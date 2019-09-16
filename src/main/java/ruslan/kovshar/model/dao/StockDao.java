package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;

import java.util.Optional;

public interface StockDao extends GenericDao<Stock> {
    Optional<Stock> findByProduct(Product product);
}
