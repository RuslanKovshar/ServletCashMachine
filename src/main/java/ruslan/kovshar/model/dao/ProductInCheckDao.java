package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.ProductInCheck;

import java.util.Optional;
import java.util.Set;

public interface ProductInCheckDao extends GenericDao<ProductInCheck> {
    Set<ProductInCheck> findAllByCheck(Check check);
}
