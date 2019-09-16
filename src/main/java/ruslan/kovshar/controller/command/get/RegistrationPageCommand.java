package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.RequestParams;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(RequestParams.SUCCESS) != null) {
            request.setAttribute(RequestParams.SUCCESS,true);
        }
        if (request.getParameter(RequestParams.ERROR) != null) {
            request.setAttribute(RequestParams.ERROR,true);
        }
        return Pages.REGISTRATION_PAGE;
    }
}
