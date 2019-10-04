package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.User;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private User user;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        paymentService = PaymentService.getInstance();
        user = new User.Builder()
                .id(1L)
                .email("example@mail.com")
                .password("1111")
                .firstNameUA("Джон")
                .firstNameEN("Jon")
                .secondNameUA("Сноу")
                .secondNameEN("Snow")
                .userCash(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void getInstance() {
        assertNotNull(PaymentService.getInstance());
    }

    @Test
    public void makePay() {
        Buyer buyer = new Buyer("New Buyers","1111111111111111");
        buyer.setId(4L);
        paymentService.makePay(buyer, user);
    }

    @Test
    public void returnMoney() {
        paymentService.returnMoney(user);
    }
}