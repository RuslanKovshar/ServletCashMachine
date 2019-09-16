package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.StockDao;
import ruslan.kovshar.model.dao.mapper.StockMapper;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JdbcStockDao implements StockDao {
    private Connection connection;

    public JdbcStockDao(Connection connection) {
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
            e.printStackTrace();
        }
    }

    @Override
    public Stock findById(Long id) {
        return null;
    }

    @Override
    public List<Stock> findAll() {
        return null;
    }

    @Override
    public void update(Stock entity) {
        try (final PreparedStatement ps = connection.prepareStatement(SQL.UPDATE_STOCK)) {
            ps.setInt(1, entity.getCountOfProduct());
            ps.setLong(2, entity.getProduct().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

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
            e.printStackTrace();
        }
        return result;
    }
}
