package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.Params;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {

    /**
     * displays registration page
     *
     * @param request http servlet request
     * @return registration page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(Params.SUCCESS) != null) {
            request.setAttribute(Params.SUCCESS,true);
        }
        return Pages.REGISTRATION_PAGE;
    }
}
