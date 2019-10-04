package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.service.PaymentService;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class PaymentCommand implements Command {

    private CheckService checkService = CheckService.getInstance();
    private PaymentService paymentService = PaymentService.getInstance();

    /**
     * makes payment and closes check
     *
     * @param request http servlet request
     * @return redirect to home page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Params.USER);
        Check check = (Check) session.getAttribute(Params.CHECK);
        String nameOnCard = request.getParameter(Params.NAME_ON_CARD);
        String cardNumber = request.getParameter(Params.CARD_NUMBER);

        BigDecimal value = new BigDecimal(request.getParameter(Params.VALUE));
        Buyer buyer = new Buyer(nameOnCard, cardNumber);

        user.setUserCash(user.getUserCash().add(value));
        paymentService.makePay(buyer, user);

        check.setUser(user);
        check.setBuyer(buyer);
        checkService.createCheck(check);
        session.removeAttribute(Params.CHECK);
        return URI.REDIRECT + request.getServletPath() + URI.HOME;
    }
}
