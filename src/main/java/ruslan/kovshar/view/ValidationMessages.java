package ruslan.kovshar.view;

/**
 * contains validation errors and messages
 */
public interface ValidationMessages {
    String ERROR = "Error";
    String EMAIL_ERROR = "email" + ERROR;
    String PASSWORD_ERROR = "password" + ERROR;
    String FIRST_NAME_EN_ERROR = "firstNameEN" + ERROR;
    String FIRST_NAME_UA_ERROR = "firstNameUA" + ERROR;
    String SECOND_NAME_EN_ERROR = "secondNameEN" + ERROR;
    String SECOND_NAME_UA_ERROR = "secondNameUA" + ERROR;

    String EMAIL_MESSAGE = "input.email.message";
    String PASSWORD_MESSAGE = "input.password.message";
    String FIRST_NAME_EN_MESSAGE = "input.firstNameEN.message";
    String FIRST_NAME_UA_MESSAGE = "input.firstNameUA.message";
    String SECOND_NAME_EN_MESSAGE = "input.secondNameEN.message";
    String SECOND_NAME_UA_MESSAGE = "input.secondNameUA.message";

    String CODE_ERROR = "code" + ERROR;
    String NAME_ERROR = "name" + ERROR;
    String PRICE_ERROR = "price" + ERROR;
    String COUNT_OF_PRODUCT_ERROR = "countOfProduct" + ERROR;

    String NAME_MESSAGE = "input.name.message";
    String CODE_MESSAGE = "input.code.message";
    String PRICE_MESSAGE = "input.price.message";
    String COUNT_OF_PRODUCT_MESSAGE = "input.count.of.product.message";

}
