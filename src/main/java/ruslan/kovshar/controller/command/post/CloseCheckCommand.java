package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CloseCheckCommand implements Command {

    /**
     * redirects to payment page
     *
     * @param request http servlet request
     * @return redirect to payment page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Check check = (Check) session.getAttribute(Params.CHECK);
        return URI.REDIRECT + request.getServletPath() + URI.PAYMENT + Params.PARAM + Params.VALUE + "=" + check.getTotalPrice();
    }
}
