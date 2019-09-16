package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.RequestParams;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(RequestParams.ERROR) != null) {
            request.setAttribute(RequestParams.ERROR, true);
        }
        if (request.getParameter(RequestParams.LOGOUT) != null) {
            request.setAttribute(RequestParams.LOGOUT, true);
        }
        return Pages.LOGIN_PAGE;
    }
}
