package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.Params;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {

    /**
     * displays login page
     *
     * @param request http servlet request
     * @return login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(Params.ERROR) != null) {
            request.setAttribute(Params.ERROR, true);
        }
        if (request.getParameter(Params.LOGOUT) != null) {
            request.setAttribute(Params.LOGOUT, true);
        }
        return Pages.LOGIN_PAGE;
    }
}
