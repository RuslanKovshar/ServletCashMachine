package ruslan.kovshar.controller.validator;

import java.util.Map;

/**
 * Interface of Validator
 *
 * @param <T> object that will be validated
 */
public interface Validator<T> {
    /**
     * validates the entity
     *
     * @param entity entity
     */
    void validate(T entity);

    /**
     * checks for errors of validation
     *
     * @return true if errors occurs, false if not
     */
    boolean hasErrors();

    /**
     * @return map with errors and messages
     */
    Map<String, String> getErrors();
}
