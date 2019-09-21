package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;

public class AllChecksPageCommand implements Command {

    private CheckService checkService;

    public AllChecksPageCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("checks", checkService.getAllUserChecks(user));
        return Pages.ALL_CHECKS_PAGE;
    }
}
