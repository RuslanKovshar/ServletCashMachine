package ruslan.kovshar.model.dao.impl;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.textconstants.SQL;

import java.sql.*;
import java.util.List;

/**
 * serves to access buyer in database
 */
public class JDBCBuyerDao implements BuyerDao {

    private static final Logger log = Logger.getLogger(JDBCBuyerDao.class);

    private Connection connection;

    JDBCBuyerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Buyer entity) {
        try (PreparedStatement ps = connection.prepareStatement(SQL.INSERT_NEW_BUYER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getNameOnCard());
            ps.setString(2, entity.getCardNumber());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public Buyer findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Buyer> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Buyer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(SQL.DELETE_BUYER)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
