package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.Params;

import javax.servlet.http.HttpServletRequest;

public class CheckPageCommand implements Command {
    /**
     * displays check page
     *
     * @param request http servlet request
     * @return check page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(Params.ERROR) != null) {
            request.setAttribute(Params.ERROR, true);
        }
        return Pages.CHECK_PAGE;
    }
}
