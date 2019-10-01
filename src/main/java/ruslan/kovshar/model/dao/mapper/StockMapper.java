package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * extracts stock from result set
 */
public class StockMapper extends Mapper<Stock> {
    @Override
    public Stock extractFromResultSet(ResultSet rs) throws SQLException {
        Stock stock = new Stock();
        stock.setId(rs.getLong("row_id"));
        stock.setCountOfProduct(rs.getInt("count_of_product"));
        return makeUnique(stock, stock.getId());
    }
}
