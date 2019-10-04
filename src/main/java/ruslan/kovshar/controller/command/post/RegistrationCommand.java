package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.dto.UserDTO;
import ruslan.kovshar.controller.validator.UserValidator;
import ruslan.kovshar.controller.validator.Validator;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;

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
        UserDTO userDTO = getUserFromRequest(request);
        Validator<UserDTO> validator = new UserValidator();
        validator.validate(userDTO);
        if (validator.hasErrors()) {
            request.setAttribute(Params.USER, userDTO);
            validator.getErrors().forEach(request::setAttribute);
            return Pages.REGISTRATION_PAGE;
        }
        User user = userService.createUser(userDTO);
        if (userService.addUser(user, Roles.CASHIER)) {
            return URI.REDIRECT + request.getServletPath() + URI.REGISTRATION + Params.PARAM + Params.SUCCESS;
        } else {
            request.setAttribute(Params.USER, userDTO);
            request.setAttribute(Params.ERROR, true);
            return Pages.REGISTRATION_PAGE;
        }
    }

    /**
     * executes the userDTO from request
     *
     * @param request http servlet request
     * @return userDTO
     */
    private UserDTO getUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter(Params.EMAIL);
        String password = request.getParameter(Params.PASSWORD);
        String firstNameUA = request.getParameter(Params.FIRST_NAME_UA);
        String secondNameUA = request.getParameter(Params.SECOND_NAME_UA);
        String firstNameEN = request.getParameter(Params.FIRST_NAME_EN);
        String secondNameEN = request.getParameter(Params.SECOND_NAME_EN);

        return new UserDTO(email, password, firstNameUA, secondNameUA, firstNameEN, secondNameEN);
    }
}
