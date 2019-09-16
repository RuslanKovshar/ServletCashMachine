package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email        = request.getParameter(RequestParams.EMAIL);
        String password     = request.getParameter(RequestParams.PASSWORD);
        String firstNameUA  = request.getParameter(RequestParams.FIRST_NAME_UA);
        String secondNameUA = request.getParameter(RequestParams.SECOND_NAME_UA);
        String firstNameEN  = request.getParameter(RequestParams.FIRST_NAME_EN);
        String secondNameEN = request.getParameter(RequestParams.SECOND_NAME_EN);

        User user = new User(email,
                Encoder.encodePassword(password),
                firstNameUA,
                secondNameUA,
                firstNameEN,
                secondNameEN);

        try {
            userService.addUser(user, Roles.CASHIER);

            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + RequestParams.PARAM + RequestParams.SUCCESS;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + RequestParams.PARAM + RequestParams.ERROR;
        }
    }
}
