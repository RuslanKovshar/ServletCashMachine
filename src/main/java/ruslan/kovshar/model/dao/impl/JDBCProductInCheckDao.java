package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.dao.mapper.ProductInCheckMapper;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCProductInCheckDao implements ProductInCheckDao {

    private Connection connection;

    public JDBCProductInCheckDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ProductInCheck entity) {
        try (PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_PRODUCT_IN_CHECK)) {
            ps.setBigDecimal(1, entity.getPrice());
            ps.setInt(2, entity.getValue());
            ps.setLong(3, entity.getCheck().getId());
            ps.setLong(4, entity.getProduct().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductInCheck findById(Long id) {
        return null;
    }

    @Override
    public List<ProductInCheck> findAll() {
        return null;
    }

    @Override
    public void update(ProductInCheck entity) {

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
    public Set<ProductInCheck> findAllByCheck(Check check) {
        Set<ProductInCheck> result = new HashSet<>();
        try (final PreparedStatement ps = connection.prepareStatement(SQL.SELECT_PRODUCTS_BY_CHECK)) {
            ps.setLong(1, check.getId());
            final ResultSet resultSet = ps.executeQuery();
            ProductInCheckMapper mapper = new ProductInCheckMapper();
            while (resultSet.next()) {
                result.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.forEach(productInCheck -> System.out.println(productInCheck.getProduct()));
        return result;
    }
}
