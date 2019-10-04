package ruslan.kovshar.controller.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerValidatorTest {

    @Test
    public void validate() {
        Integer validate = IntegerValidator.validate("1111");
        assertNotNull(validate);
        assertEquals(validate.intValue(),1111);
    }

    @Test
    public void validationFailed() {
        Integer validate = IntegerValidator.validate("abc");
        assertNull(validate);
    }
}