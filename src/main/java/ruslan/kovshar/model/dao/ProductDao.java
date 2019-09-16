package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Product;

import java.util.Optional;

public interface ProductDao extends GenericDao<Product> {
    Optional<Product> findByCode(Integer code);
    Optional<Product> findByName(String name);
}
