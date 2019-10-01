package ruslan.kovshar.model.exceptions;

/**
 * occurs if resource was not found in database
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
