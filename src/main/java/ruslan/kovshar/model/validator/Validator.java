package ruslan.kovshar.model.validator;

public class Validator {

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
