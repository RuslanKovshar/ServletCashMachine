package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter(Params.EMAIL);
        String password = request.getParameter(Params.PASSWORD);
        Optional<User> checkUserForLogin = userService.getUser(email, Encoder.encodePassword(password));
        if (checkUserForLogin.isPresent()) {
            User user = checkUserForLogin.get();

            System.out.println(user.getAuthorities());

            session.setAttribute("user", user);

            String redirect = (String) session.getAttribute("redirectURI");

            if (redirect == null || redirect.equals("/")) redirect = URI.API + URI.HOME;

            session.removeAttribute("redirectURI");
            return URI.REDIRECT + redirect;
        } else {
            return URI.REDIRECT + request.getServletPath() + URI.LOGIN + Params.PARAM + Params.ERROR;
        }
    }
}
