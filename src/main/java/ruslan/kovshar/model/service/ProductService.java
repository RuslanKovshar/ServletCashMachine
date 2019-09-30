package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.exceptions.ProductExistException;
import ruslan.kovshar.model.exceptions.ResourceNotFoundException;

import static ruslan.kovshar.view.ExceptionMessages.PRODUCT_NOT_FOUND;

public class ProductService {

    private static ProductService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }

    public boolean createProduct(Product product) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
            return true;
        } catch (ProductExistException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product findProductByCode(Integer code) {
        try(final ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findByCode(code).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        }

    }

    public Product findProductByName(String name) {
        try(final ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findByName(name).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        }
    }
}
