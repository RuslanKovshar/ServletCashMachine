package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract CheckDao createCheckDao();

    public abstract ProductDao createProductDao();

    public abstract RoleDao createRoleDao();

    public abstract StockDao createStockDao();

    public abstract UserDao createUserDao();

    public abstract ProductInCheckDao createProductInCheckDao();

    public abstract BuyerDao createBuyerDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
