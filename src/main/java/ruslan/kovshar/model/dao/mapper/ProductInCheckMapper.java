package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.ProductInCheck;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * extracts product in check from result set
 */
public class ProductInCheckMapper extends Mapper<ProductInCheck> {
    @Override
    public ProductInCheck extractFromResultSet(ResultSet rs) throws SQLException {
        ProductInCheck productInCheck = new ProductInCheck();
        ProductMapper productMapper = new ProductMapper();

        productInCheck.setId(rs.getLong("row_id"));
        productInCheck.setPrice(rs.getBigDecimal("price"));
        productInCheck.setValue(rs.getInt("value"));
        productInCheck.setProduct(productMapper.extractFromResultSet(rs));
        return makeUnique(productInCheck, productInCheck.getId());
    }
}
