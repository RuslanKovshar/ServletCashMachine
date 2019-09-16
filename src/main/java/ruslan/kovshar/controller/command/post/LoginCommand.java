package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.RoleService;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter(RequestParams.EMAIL);
        String password = request.getParameter(RequestParams.PASSWORD);
        Optional<User> checkUserForLogin = userService.getUser(email, Encoder.encodePassword(password));
        if (checkUserForLogin.isPresent()) {
            User user = checkUserForLogin.get();
            user.setAuthorities(roleService.getUserRoles(user));
            session.setAttribute("user", user);
            String redirect = (String) session.getAttribute("redirectURI");
            if (redirect.equals("/")) redirect = URI.API + URI.HOME;
            session.removeAttribute("redirectURI");
            return URI.REDIRECT + redirect;
        } else {
            return URI.REDIRECT + request.getServletPath() + URI.LOGIN + RequestParams.PARAM + RequestParams.ERROR;
        }
    }
}
