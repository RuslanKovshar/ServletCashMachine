package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.model.exceptions.TransactionException;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CheckAddProduct implements Command {

    private StockService stockService = StockService.getInstance();

    /**
     * adds product to check
     *
     * @param request http servlet request
     * @return redirect to check page if product is on stock,
     * redirect to product page with error if not
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer value = Integer.parseInt(request.getParameter("number"));
        Product product = (Product) session.getAttribute("product");
        Check check = (Check) session.getAttribute("check");

        if (!productIsOnStock(product, value)) {
            return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT + Params.PARAM + Params.ERROR;
        }

        Optional<ProductInCheck> productInCheck = check.getProducts().stream().filter(s -> s.getProduct().equals(product)).findAny();

        if (productInCheck.isPresent()) {
            ProductInCheck checkProduct = productInCheck.get();
            checkProduct.setValue(checkProduct.getValue() + value);
            checkProduct.setPrice(checkProduct.getPrice().add(product.calculatePrice(value)));
        } else {
            ProductInCheck newProduct = new ProductInCheck();
            newProduct.setProduct(product);
            newProduct.setValue(value);
            newProduct.setPrice(product.calculatePrice(value));
            newProduct.setCheck(check);
            check.getProducts().add(newProduct);
        }

        check.calculateTotalPrice();
        session.removeAttribute("product");
        return URI.REDIRECT + request.getServletPath() + URI.CHECK;
    }

    /**
     * checks count of product on the stock
     *
     * @param product        product
     * @param countOfProduct count of product
     * @return true count of product is on stock, false if not
     */
    private boolean productIsOnStock(Product product, Integer countOfProduct) {
        try {
            stockService.updateStock(product, countOfProduct);
            return true;
        } catch (TransactionException e) {
            //log.error(TRANSACTION_ERROR);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
