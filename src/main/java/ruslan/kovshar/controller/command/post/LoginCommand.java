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

    /**
     * checks the user and set him to session
     *
     * @param request http servlet request
     * @return redirect to redirectURI if user exist,
     * redirect to login page with error if not
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = request.getParameter(Params.EMAIL);
        String password = request.getParameter(Params.PASSWORD);
        Optional<User> checkUserForLogin = userService.getUser(email, Encoder.encodePassword(password));
        if (checkUserForLogin.isPresent()) {
            User user = checkUserForLogin.get();
            session.setAttribute(Params.USER, user);
            String redirectURI = (String) session.getAttribute(Params.REDIRECTED_URI);
            if (redirectURI == null || redirectURI.equals(URI.HOME)) redirectURI = URI.API + URI.HOME;
            session.removeAttribute(Params.REDIRECTED_URI);
            return URI.REDIRECT + redirectURI;
        } else {
            return URI.REDIRECT + request.getServletPath() + URI.LOGIN + Params.PARAM + Params.ERROR;
        }
    }
}
