package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;

import javax.servlet.http.HttpServletRequest;

public class EditUserPageCommand implements Command {
    private UserService userService = UserService.getInstance();

    /**
     * displays user editor page
     *
     * @param request http servlet request
     * @return user edit page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idParam = request.getParameter(Params.ID);
        Long id = Long.parseLong(idParam);
        User user = userService.getUserById(id);
        request.setAttribute(Params.USER, user);
        return Pages.EDIT_USER_PAGE;
    }
}
