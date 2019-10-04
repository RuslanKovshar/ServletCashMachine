package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EditUserCommand implements Command {

    private UserService userService = UserService.getInstance();

    /**
     * changes user roles
     *
     * @param request http servlet request
     * @return redirect to users page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String[] roles = request.getParameterValues(Params.ROLES);
        Set<Roles> newRoles = Arrays.stream(roles).map(Roles::valueOf).collect(Collectors.toSet());
        String idParam = request.getParameter(Params.ID);
        Long id = Long.parseLong(idParam);
        User user = userService.getUserById(id);
        user.setAuthorities(newRoles);
        userService.updateUser(user);
        return URI.REDIRECT + request.getServletPath() + URI.USERS;
    }
}
