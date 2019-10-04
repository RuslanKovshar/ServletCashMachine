package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.dao.ProductInCheckDao;
import ruslan.kovshar.model.dao.mapper.ProductInCheckMapper;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.textconstants.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * serves to access product in check in database
 */
public class JDBCProductInCheckDao implements ProductInCheckDao {

    private static final Logger log = Logger.getLogger(JDBCProductInCheckDao.class);

    private Connection connection;

    JDBCProductInCheckDao(Connection connection) {
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
            log.error(e);
        }
    }

    @Override
    public ProductInCheck findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ProductInCheck> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(ProductInCheck entity) {
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
            log.error(e);
        }
        return result;
    }
}
