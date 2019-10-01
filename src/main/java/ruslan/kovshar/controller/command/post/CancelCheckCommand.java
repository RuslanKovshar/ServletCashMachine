package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.service.PaymentService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class CancelCheckCommand implements Command {

    private CheckService checkService = CheckService.getInstance();
    private StockService stockService = StockService.getInstance();
    private PaymentService paymentService = PaymentService.getInstance();

    /**
     * cancels check and returns money to buyer
     *
     * @param request http servlet request
     * @return redirect to home page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Params.USER);
        long id = Long.parseLong(request.getParameter(Params.ID));
        Check check = checkService.findCheckById(id);
        check.getProducts().forEach(productInCheck -> {
            try {
                stockService.updateStock(productInCheck.getProduct(), -productInCheck.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        user.setUserCash(user.getUserCash().subtract(check.getTotalPrice().multiply(BigDecimal.valueOf(-1))));
        paymentService.returnMoney(user);
        checkService.deleteCheck(check);
        return URI.REDIRECT + request.getServletPath() + URI.HOME;
    }
}
