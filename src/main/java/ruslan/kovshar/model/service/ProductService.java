package ruslan.kovshar.model.service;

import org.apache.log4j.Logger;
import ruslan.kovshar.controller.dto.ProductDTO;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.ProductDao;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.exceptions.ProductExistException;
import ruslan.kovshar.model.exceptions.ResourceNotFoundException;

import static ruslan.kovshar.textconstants.ExceptionMessages.PRODUCT_NOT_FOUND;

public class ProductService {

    private static final Logger log = Logger.getLogger(ProductService.class);

    private static volatile ProductService instance;
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private ProductService() {
    }

    /**
     * @return instance
     */
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

    /**
     * saves the product
     *
     * @param product product
     * @return true if product was saved, false if not
     */
    public boolean createProduct(Product product) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
            return true;
        } catch (ProductExistException e) {
            log.error(e);
        }
        return false;
    }

    /**
     * finds product by code
     *
     * @param code code of product
     * @return product
     */
    public Product findProductByCode(Integer code) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findByCode(code).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        }

    }

    /**
     * finds product by name
     *
     * @param name name of product
     * @return product
     */
    public Product findProductByName(String name) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findByName(name).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
        }
    }

    /**
     * creates product from productDTO
     *
     * @param productDTO product info
     * @return product
     */
    public Product createProduct(ProductDTO productDTO) {
        Product product;
        if (productDTO.getType().equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(productDTO.getCode(),
                    productDTO.getName(),
                    productDTO.getPrice(),
                    productDTO.getType());
        } else {
            product = new WeightProduct(productDTO.getCode(),
                    productDTO.getName(),
                    productDTO.getPrice(),
                    productDTO.getType());
        }
        return product;
    }
}
