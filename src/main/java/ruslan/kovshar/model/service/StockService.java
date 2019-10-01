package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.StockDao;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.exceptions.ResourceNotFoundException;
import ruslan.kovshar.model.exceptions.TransactionException;

import static ruslan.kovshar.view.ExceptionMessages.STOCK_NOT_FOUND;

public class StockService {

    private static StockService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private StockService() {
    }

    /**
     * @return instance
     */
    public static StockService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new StockService();
                }
            }
        }
        return instance;
    }

    /**
     * adds product to the stock
     *
     * @param stock stock
     */
    public void addProductToStock(Stock stock) {
        try (StockDao stockDao = daoFactory.createStockDao()) {
            stockDao.create(stock);
        }
    }

    /**
     * updates count of product in the stock
     *
     * @param product product
     * @param count   count of product
     * @throws ResourceNotFoundException if no product in stock,
     *                                   TransactionException if no such count of product in stock
     */
    public void updateStock(Product product, Integer count) throws Exception {
        try (final StockDao stockDao = daoFactory.createStockDao()) {
            final Stock stock = stockDao.findByProduct(product)
                    .orElseThrow(() -> new ResourceNotFoundException(STOCK_NOT_FOUND));
            stock.setProduct(product);
            final Integer oldCount = stock.getCountOfProduct();
            int newCount = oldCount - count;
            if (newCount < 0) {
                throw new TransactionException();
            }
            stock.setCountOfProduct(newCount);
            stockDao.update(stock);
        }
    }
}
