package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.controller.dto.UserDTO;
import ruslan.kovshar.controller.security.Encoder;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        userService = UserService.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(UserService.getInstance());
    }

    @Test
    public void addUser() {
        User user = new User.Builder()
                .id(3L)
                .email("newUser@mail.com")
                .password("newUsersPass")
                .firstNameUA("новий")
                .firstNameEN("new")
                .secondNameUA("користувач")
                .secondNameEN("user")
                .userCash(BigDecimal.ZERO)
                .build();
        userService.addUser(user, Roles.CASHIER);
        Optional<User> newUser = userService.getUser("newUser@mail.com", "newUsersPass");
        assertTrue(newUser.isPresent());
        assertTrue(newUser.get().getAuthorities().contains(Roles.CASHIER));
    }

    @Test
    public void getUserExist() {
        Optional<User> user = userService.getUser("ruslan.kovshar@gmail.com", "2222");
        assertTrue(user.isPresent());
    }

    @Test
    public void getUserNotExist() {
        Optional<User> user = userService.getUser("notExisted@mail.com", "1111");
        assertFalse(user.isPresent());
    }

    @Test
    public void updateUser() {
        String newPass = "newPass";
        User user = new User.Builder()
                .id(1L)
                .email("example@mail.com")
                .password(newPass)
                .firstNameUA("Джон")
                .firstNameEN("Jon")
                .secondNameUA("Сноу")
                .secondNameEN("Snow")
                .userCash(BigDecimal.ZERO)
                .build();
        userService.updateUser(user);
        Optional<User> userWithChangedPass = userService.getUser("example@mail.com", newPass);
        assertTrue(userWithChangedPass.isPresent());
    }

    @Test
    public void createUser() {
        UserDTO userDTO = new UserDTO("email", "pass", "fnua", "fnen", "snua", "snen");
        User user = userService.createUser(userDTO);
        assertNotNull(user);
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(Encoder.encodePassword(userDTO.getPassword()), user.getPassword());
        assertEquals(userDTO.getFirstNameEN(), user.getFirstNameEN());
        assertEquals(userDTO.getFirstNameUA(), user.getFirstNameUA());
        assertEquals(userDTO.getSecondNameUA(), user.getSecondNameUA());
        assertEquals(userDTO.getSecondNameEN(), user.getSecondNameEN());
    }
}