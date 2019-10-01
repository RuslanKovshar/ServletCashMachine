package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;

import java.util.Optional;

/**
 * Stock DAO
 */
public interface StockDao extends GenericDao<Stock> {
    /**
     * finds stock by product
     *
     * @param product product
     * @return stock
     */
    Optional<Stock> findByProduct(Product product);
}
