package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class RegistrationCommand implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(Params.EMAIL);
        String password = request.getParameter(Params.PASSWORD);
        String firstNameUA = request.getParameter(Params.FIRST_NAME_UA);
        String secondNameUA = request.getParameter(Params.SECOND_NAME_UA);
        String firstNameEN = request.getParameter(Params.FIRST_NAME_EN);
        String secondNameEN = request.getParameter(Params.SECOND_NAME_EN);

        User user = new User.Builder()
                .email(email)
                .password(Encoder.encodePassword(password))
                .firstNameUA(firstNameUA)
                .firstNameEN(firstNameEN)
                .secondNameUA(secondNameUA)
                .secondNameEN(secondNameEN)
                .userCash(BigDecimal.ZERO)
                .build();

        if (userService.addUser(user, Roles.CASHIER)) {
            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + Params.PARAM + Params.SUCCESS;
        } else {
            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + Params.PARAM + Params.ERROR;
        }
    }
}
