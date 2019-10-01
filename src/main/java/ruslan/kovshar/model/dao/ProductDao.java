package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Product;

import java.util.Optional;

/**
 * Product DAO
 */
public interface ProductDao extends GenericDao<Product> {
    /**
     * finds product by code
     *
     * @param code product code
     * @return product
     */
    Optional<Product> findByCode(Integer code);

    /**
     * finds product by name
     *
     * @param name product name
     * @return product
     */
    Optional<Product> findByName(String name);
}
