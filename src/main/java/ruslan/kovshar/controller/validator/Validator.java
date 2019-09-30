package ruslan.kovshar.controller.validator;

import java.util.Map;

public interface Validator<T> {
    void validate(T entity);
    boolean hasErrors();
    Map<String,String> getErrors();
}
