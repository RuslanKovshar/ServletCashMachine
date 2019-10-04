package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;

import javax.servlet.http.HttpServletRequest;

public class MerchandiserPageCommand implements Command {

    /**
     * displays merchandiser page
     *
     * @param request http servlet request
     * @return merchandiser page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(Params.PRODUCT_EXIST) != null) {
            request.setAttribute(Params.PRODUCT_EXIST, true);
        }
        if (request.getParameter(Params.PRODUCT_NOT_FOUND) != null) {
            request.setAttribute(Params.PRODUCT_NOT_FOUND, true);
        }
        return Pages.MERCHANDISER_PAGE;
    }
}
