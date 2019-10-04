package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.dao.StockDao;
import ruslan.kovshar.model.dao.mapper.StockMapper;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.textconstants.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * serves to access stock in database
 */
public class JDBCStockDao implements StockDao {

    private static final Logger log = Logger.getLogger(JDBCStockDao.class);

    private Connection connection;

    JDBCStockDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Stock entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(SQL.ADD_PRODUCT_TO_STOCK)) {
            ps.setInt(1, entity.getCountOfProduct());
            ps.setLong(2, entity.getProduct().getId());
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public Stock findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Stock> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Stock entity) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.UPDATE_STOCK)) {
            ps.setInt(1, entity.getCountOfProduct());
            ps.setLong(2, entity.getProduct().getId());
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public Optional<Stock> findByProduct(Product product) {
        Optional<Stock> result = Optional.empty();
        try (final PreparedStatement ps = connection.prepareStatement(SQL.SELECT_STOCK_BY_PRODUCT)) {
            ps.setLong(1, product.getId());
            final ResultSet resultSet = ps.executeQuery();
            StockMapper stockMapper = new StockMapper();
            while (resultSet.next()) {
                result = Optional.of(stockMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }
}
