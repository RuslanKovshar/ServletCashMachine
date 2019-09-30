package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CheckDao createCheckDao() {
        return new JDBCCheckDao(getConnection());
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao(getConnection());
    }

    @Override
    public RoleDao createRoleDao() {
        return new JDBCRoleDao(getConnection());
    }

    @Override
    public StockDao createStockDao() {
        return new JDBCStockDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ProductInCheckDao createProductInCheckDao() {
        return new JDBCProductInCheckDao(getConnection());
    }

    @Override
    public BuyerDao createBuyerDao() {
        return new JDBCBuyerDao(getConnection());
    }
}
