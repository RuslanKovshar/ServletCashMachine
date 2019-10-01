package ruslan.kovshar.controller.validator;

public class IntegerValidator {

    /**
     * parses the string param
     *
     * @param param value to validate
     * @return integer value if was parsed, null if not
     */
    public static Integer validate(String param) {
        Integer result;
        try {
            result = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }
}
