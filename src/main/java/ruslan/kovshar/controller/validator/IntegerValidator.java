package ruslan.kovshar.controller.validator;

public class IntegerValidator {

    public static Integer integerValidator(String param) {
        Integer result;
        try {
            result = Integer.parseInt(param);
        }catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }
}
