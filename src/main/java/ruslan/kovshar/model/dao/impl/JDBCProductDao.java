package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.dao.mapper.ProductMapper;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.exceptions.ProductExistException;
import ruslan.kovshar.textconstants.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * serves to access product in database
 */
public class JDBCProductDao implements ProductDao {

    private static final Logger log = Logger.getLogger(JDBCProductDao.class);

    private Connection connection;

    JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(SQL.INSERT_NEW_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getCode());
            ps.setString(2, entity.getName());
            ps.setBigDecimal(3, entity.getPrice());
            ps.setString(4, entity.getType().name());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new ProductExistException();
        }

    }

    @Override
    public Product findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Product entity) {
        throw new UnsupportedOperationException();
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
    public Optional<Product> findByCode(Integer code) {
        Optional<Product> result = Optional.empty();
        try (final PreparedStatement ps = connection.prepareStatement(SQL.SELECT_PRODUCT_BY_CODE)) {
            ps.setInt(1, code);
            final ResultSet resultSet = ps.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> result = Optional.empty();
        try (final PreparedStatement ps = connection.prepareStatement(SQL.SELECT_PRODUCT_BY_NAME)) {
            ps.setString(1, name);
            final ResultSet resultSet = ps.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                result = Optional.of(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }
}
