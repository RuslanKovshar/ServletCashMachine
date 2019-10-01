package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.ProductInCheck;

import java.util.Set;

/**
 * ProductInCheck DAO
 */
public interface ProductInCheckDao extends GenericDao<ProductInCheck> {
    /**
     * finds all product in given check
     *
     * @param check check
     * @return set of all products in check
     */
    Set<ProductInCheck> findAllByCheck(Check check);
}
