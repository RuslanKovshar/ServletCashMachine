package ruslan.kovshar.controller.validator;

import org.junit.Before;
import org.junit.Test;
import ruslan.kovshar.controller.dto.UserDTO;

import java.util.Map;

import static org.junit.Assert.*;

public class UserValidatorTest {

    private Validator<UserDTO> validator;

    @Before
    public void setUp() {
        validator = new UserValidator();
    }

    @Test
    public void validate() {
        validator.validate(new UserDTO("goodmail@gmail.com",
                "Rjdifh_1999",
                "Руслан",
                "Ковшар",
                "Ruslan",
                "Kovshar"));
        assertFalse(validator.hasErrors());
    }

    @Test
    public void validateError() {
        validator.validate(new UserDTO("goodmail",
                "1111",
                "Руслан",
                "Ковшар",
                "Ruslan",
                "Kovshar"));
        assertTrue(validator.hasErrors());
    }

    @Test
    public void hasErrors() {
        boolean hasErrors = validator.hasErrors();
        assertFalse(hasErrors);
    }

    @Test
    public void getErrors() {
        Map<String, String> errors = validator.getErrors();
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
    }
}