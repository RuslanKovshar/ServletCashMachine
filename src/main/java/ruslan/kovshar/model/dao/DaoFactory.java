package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.dao.impl.JDBCDaoFactory;

/**
 * abstract DaoFactory
 */
public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    /**
     * creates CheckDao
     *
     * @return implementation of CheckDao
     */
    public abstract CheckDao createCheckDao();

    /**
     * creates ProductDao
     *
     * @return implementation of ProductDao
     */
    public abstract ProductDao createProductDao();

    /**
     * creates RoleDao
     *
     * @return implementation of RoleDao
     */
    public abstract RoleDao createRoleDao();

    /**
     * creates StockDao
     *
     * @return implementation of StockDao
     */
    public abstract StockDao createStockDao();

    /**
     * creates UserDao
     *
     * @return implementation of UserDao
     */
    public abstract UserDao createUserDao();

    /**
     * creates RoleDao
     *
     * @return implementation of RoleDao
     */
    public abstract ProductInCheckDao createProductInCheckDao();

    /**
     * creates BuyerDao
     *
     * @return implementation of BuyerDao
     */
    public abstract BuyerDao createBuyerDao();

    /**
     * @return instance of DaoFactory
     */
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
