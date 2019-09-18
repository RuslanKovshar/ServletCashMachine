package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;

public class CheckPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Pages.CHECK_PAGE;
    }
}