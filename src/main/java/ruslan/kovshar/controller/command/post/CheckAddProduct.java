package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.model.exceptions.TransactionException;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CheckAddProduct implements Command {

    private StockService stockService = new StockService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer value = Integer.parseInt(request.getParameter("number"));
        Product product = (Product) session.getAttribute("product");
        Check check = (Check) session.getAttribute("check");

        try {
            stockService.updateStock(product, value);
        } catch (TransactionException e) {
            //log.error(TRANSACTION_ERROR);
            return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT + RequestParams.PARAM + RequestParams.ERROR;
        } catch (Exception e) {
            e.printStackTrace();
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
          /*  if (product.getType().equals(Types.PIECE_PRODUCT)) {
                newProduct.setValue(number.intValue());
            } else {
                newProduct.setValue(number.doubleValue());
            }*/
            newProduct.setPrice(product.calculatePrice(value));
            newProduct.setCheck(check);
            check.getProducts().add(newProduct);
        }

        check.setTotalPrice(check.getTotalPrice().add(product.calculatePrice(value)));
        session.removeAttribute("product");
        return URI.REDIRECT + request.getServletPath() + URI.CHECK;
    }
}
