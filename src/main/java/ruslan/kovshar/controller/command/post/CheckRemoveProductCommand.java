package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.ProductInCheck;
import ruslan.kovshar.model.exceptions.TransactionException;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CheckRemoveProductCommand implements Command {

    private StockService stockService = StockService.getInstance();

    /**
     * removes product from check
     *
     * @param request http servlet request
     * @return redirect to check page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Check check = (Check) session.getAttribute("check");
        String name = request.getParameter("name");

        Optional<ProductInCheck> productInCheck = check.getProducts()
                .stream()
                .filter(s -> s.getProduct().getName().equals(name))
                .findAny();

        if (productInCheck.isPresent()) {
            ProductInCheck checkProduct = productInCheck.get();
            check.getProducts().remove(checkProduct);

            //todo: попробуй вынести в отдельный метод
            try {
                stockService.updateStock(checkProduct.getProduct(), -checkProduct.getValue());
            } catch (TransactionException e) {
                //log.error(TRANSACTION_ERROR);
                return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT + Params.PARAM + Params.ERROR;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        check.calculateTotalPrice();
        return URI.REDIRECT + request.getServletPath() + URI.CHECK;
    }
}
