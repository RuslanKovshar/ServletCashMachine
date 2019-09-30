package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.BuyerDao;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.view.SQL;

import java.sql.*;
import java.util.List;

public class JDBCBuyerDao implements BuyerDao {

    private Connection connection;

    public JDBCBuyerDao(Connection connection) {
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
            e.printStackTrace();
        }
    }

    @Override
    public Buyer findById(Long id) {
        return null;
    }

    @Override
    public List<Buyer> findAll() {
        return null;
    }

    @Override
    public void update(Buyer entity) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(SQL.DELETE_BUYER)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
