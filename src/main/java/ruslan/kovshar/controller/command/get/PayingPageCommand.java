package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;

public class PayingPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Pages.PAYING_PAGE;
    }
}
