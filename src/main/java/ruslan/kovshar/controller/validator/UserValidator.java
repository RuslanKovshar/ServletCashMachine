package ruslan.kovshar.controller.validator;

import ruslan.kovshar.controller.dto.UserDTO;
import ruslan.kovshar.model.entity.User;

import java.util.HashMap;
import java.util.Map;

import static ruslan.kovshar.view.ValidationMessages.*;

public class UserValidator implements Validator<UserDTO> {

    private Map<String, String> errors = new HashMap<>();
    private String emailPattern = "^([a-z0-9_.-]+)@([a-z0-9_-]+).([a-z]{2,6})$";
    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.-_])(?=\\S+$).{8,}$";

    @Override
    public void validate(UserDTO entity) {
        if (!entity.getEmail().matches(emailPattern)) {
            errors.put(EMAIL_ERROR, EMAIL_MESSAGE);
        }
        if (!entity.getPassword().matches(passwordPattern)) {
            errors.put(PASSWORD_ERROR, PASSWORD_MESSAGE);
        }
        if (entity.getFirstNameEN() == null || entity.getFirstNameEN().equals("")) {
            errors.put(FIRST_NAME_EN_ERROR, FIRST_NAME_EN_MESSAGE);
        }
        if (entity.getFirstNameUA() == null || entity.getFirstNameUA().equals("")) {
            errors.put(FIRST_NAME_UA_ERROR, FIRST_NAME_UA_MESSAGE);
        }
        if (entity.getSecondNameEN() == null || entity.getSecondNameEN().equals("")) {
            errors.put(SECOND_NAME_EN_ERROR, SECOND_NAME_EN_MESSAGE);
        }
        if (entity.getSecondNameUA() == null || entity.getSecondNameUA().equals("")) {
            errors.put(SECOND_NAME_UA_ERROR, SECOND_NAME_UA_MESSAGE);
        }
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public Map<String, String> getErrors() {
        return errors;
    }
}
