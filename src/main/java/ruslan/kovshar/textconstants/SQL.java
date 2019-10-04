package ruslan.kovshar.textconstants;

/**
 * contains sql queries
 */
public interface SQL {
    /*Sql queries for Users*/
    String INSERT_NEW_USER = "INSERT INTO users (email,password,first_name_ua,second_name_ua,first_name_en,second_name_en,cash) VALUES (?,?,?,?,?,?,?)";
    String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * from users JOIN user_roles ON users.id = user_roles.user_id WHERE email = ? AND password = ?";
    String UPDATE_USER = "UPDATE users SET " +
            "email = ?, " +
            "password =?, " +
            "first_name_ua = ?, " +
            "second_name_ua = ?," +
            "first_name_en = ?," +
            "second_name_en = ?," +
            "cash = ? " +
            "WHERE id = ?";

    /*Sql queries for Roles*/
    String INSERT_NEW_ROLE = "INSERT INTO user_roles VALUES (?,?)";
    String SELECT_ROLE = "SELECT * FROM user_roles WHERE user_id = ?";

    /*Sql queries for Products*/
    String INSERT_NEW_PRODUCT = "INSERT INTO products (code,name,price,type) VALUES (?,?,?,?)";
    String SELECT_PRODUCT_BY_CODE = "SELECT * FROM products WHERE code = ?";
    String SELECT_PRODUCT_BY_NAME = "SELECT * FROM products WHERE name = ?";

    /*Sql queries for Stock*/
    String UPDATE_STOCK = "UPDATE stock SET count_of_product = ? WHERE product_id = ?";
    String ADD_PRODUCT_TO_STOCK = "INSERT INTO stock (count_of_product,product_id) VALUES (?,?)";
    String SELECT_STOCK_BY_PRODUCT = "SELECT * FROM stock WHERE product_id = ?";

    /*Sql queries for Check*/
    String INSERT_NEW_CHECK = "INSERT INTO checks (total_price, user_id, buyer_id) VALUES (?,?,?)";
    String SELECT_ALL_CHECK = "SELECT * FROM checks";
    String SELECT_CHECK_BY_USER = "SELECT * FROM checks JOIN buyer_info ON checks.buyer_id = buyer_info.id WHERE user_id = ?";
    String SELECT_CHECK_BY_ID_WITH_BUYER = "SELECT * FROM checks JOIN buyer_info ON checks.buyer_id = buyer_info.id WHERE checks.id = ?";
    String DELETE_CHECK = "DELETE FROM checks WHERE id = ?";
    String COUNT_OF_CHECKS = "SELECT COUNT(*) FROM checks WHERE user_id = ?";

    /*Sql queries for ProductInCheck*/
    String INSERT_NEW_PRODUCT_IN_CHECK = "INSERT INTO product_in_check (price, value, check_id, product_id) VALUES (?,?,?,?)";
    String SELECT_PRODUCTS_BY_CHECK = "SELECT * FROM product_in_check JOIN products ON products.id = product_id WHERE check_id = ?";

    /*Sql queries for Buyer*/
    String INSERT_NEW_BUYER = "INSERT INTO buyer_info (name_on_card,card_number) VALUES (?,?)";
    String DELETE_BUYER = "DELETE FROM buyer_info WHERE id = ?";

}
