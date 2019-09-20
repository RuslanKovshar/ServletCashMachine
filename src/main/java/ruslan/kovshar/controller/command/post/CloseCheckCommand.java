package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CloseCheckCommand implements Command {

    private CheckService checkService;

    public CloseCheckCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Check check = (Check) session.getAttribute("check");
        User user = (User) session.getAttribute("user");
        check.setUser(user);
        checkService.createCheck(check);
        session.removeAttribute("check");
        return URI.REDIRECT + request.getServletPath() + "/";
    }
}
