package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.controller.validator.UserValidator;
import ruslan.kovshar.controller.validator.Validator;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class RegistrationCommand implements Command {

    private UserService userService = UserService.getInstance();

    /**
     * creates new user
     *
     * @param request http servlet request
     * @return redirect to registration page with success param if user created,
     * registration page with error param if not,
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = getUserFromRequest(request);
        Validator<User> validator = new UserValidator();
        validator.validate(user);
        if (validator.hasErrors()) {
            request.setAttribute("user", user);
            validator.getErrors().forEach(request::setAttribute);
            return Pages.REGISTRATION_PAGE;
        }
        user.setPassword(Encoder.encodePassword(user.getPassword()));
        if (userService.addUser(user, Roles.CASHIER)) {
            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + Params.PARAM + Params.SUCCESS;
        } else {
            request.setAttribute("user", user);
            request.setAttribute(Params.ERROR, true);
            return Pages.REGISTRATION_PAGE;
        }
    }

    /**
     * executes the user from request
     *
     * @param request http servlet request
     * @return user
     */
    private User getUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter(Params.EMAIL);
        String password = request.getParameter(Params.PASSWORD);
        String firstNameUA = request.getParameter(Params.FIRST_NAME_UA);
        String secondNameUA = request.getParameter(Params.SECOND_NAME_UA);
        String firstNameEN = request.getParameter(Params.FIRST_NAME_EN);
        String secondNameEN = request.getParameter(Params.SECOND_NAME_EN);

        return new User.Builder()
                .email(email)
                .password(password)
                .firstNameUA(firstNameUA)
                .firstNameEN(firstNameEN)
                .secondNameUA(secondNameUA)
                .secondNameEN(secondNameEN)
                .userCash(BigDecimal.ZERO)
                .build();
    }
}
