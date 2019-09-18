package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.view.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    }
}
