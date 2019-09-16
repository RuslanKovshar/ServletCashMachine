package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper extends Mapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Types type = Types.valueOf(rs.getString("type"));
        Product product;
        if (type.equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct();
        } else {
            product = new WeightProduct();
        }
        product.setId(rs.getLong("id"));
        product.setCode(rs.getInt("code"));
        product.setNameEN(rs.getString("name_en"));
        product.setNameUA(rs.getString("name_ua"));
        product.setPrice(rs.getBigDecimal("price"));
        return makeUnique(product, product.getId());
    }
}
