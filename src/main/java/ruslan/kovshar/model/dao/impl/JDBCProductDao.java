package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.dao.mapper.ProductMapper;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.exceptions.ProductExistException;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * serves to access product in database
 */
public class JDBCProductDao implements ProductDao {
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
            e.printStackTrace();
            throw new ProductExistException();
        }

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return result;
    }
}
