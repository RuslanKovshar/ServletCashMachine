package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersPageCommand implements Command {

    private UserService userService = UserService.getInstance();

    /**
     * displays users page
     *
     * @param request http servlet request
     * @return users page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute(Params.USERS, users);
        return Pages.USERS_PAGE;
    }
}
