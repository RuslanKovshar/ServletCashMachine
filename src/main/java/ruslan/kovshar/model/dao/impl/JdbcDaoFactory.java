package ruslan.kovshar.model.dao.impl;

import ruslan.kovshar.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

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
        return new JdbcCheckDao(getConnection());
    }

    @Override
    public ProductDao createProductDao() {
        return new JdbcProductDao(getConnection());
    }

    @Override
    public RoleDao createRoleDao() {
        return new JdbcRoleDao(getConnection());
    }

    @Override
    public StockDao createStockDao() {
        return new JdbcStockDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }

    @Override
    public ProductInCheckDao createProductInCheckDao() {
        return new JDBCProductInCheckDao(getConnection());
    }
}
