package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.textconstants.Pages;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command {

    /**
     * displays home page
     *
     * @param request http servlet request
     * @return home page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return Pages.HOME_PAGE;
    }
}
