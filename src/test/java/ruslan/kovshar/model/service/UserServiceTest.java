package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.model.exceptions.UserExistException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDao userDao;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User.Builder()
                .id(3L)
                .email("newUser@mail.com")
                .password("newUsersPass")
                .firstNameUA("новий")
                .firstNameEN("new")
                .secondNameUA("користувач")
                .secondNameEN("user")
                .userCash(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void addUserFailed() {
        Mockito.doThrow(UserExistException.class).when(userDao).create(user);
        assertFalse(userService.addUser(user, Roles.CASHIER));
    }

    @Test
    public void getUserNotExist() {
        String email = "ruslan.kovshar@gmail.com";
        String password = "222";
        Mockito.when(userDao.findByEmailAndPassword(email, password))
                .thenReturn(Optional.empty());
        assertEquals(userService.getUser(email, password), Optional.empty());
    }
}