package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.UserDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.exceptions.UserExistException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCUserDaoTest {

    private UserDao userDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    @After
    public void close() {
        userDao.close();
    }

    @Test
    public void findByEmailAndPassword() {
        Optional<User> user = userDao.findByEmailAndPassword("ruslan.kovshar@gmail.com", "2222");
        assertTrue(user.isPresent());
    }

    @Test
    public void findByEmailAndPasswordFail() {
        Optional<User> user = userDao.findByEmailAndPassword("wrongMail", "2222");
        assertFalse(user.isPresent());
    }

    @Test
    public void create() {
        User user = getUser("newUser@mail.com");
        userDao.create(user);
        assertNotNull(user.getId());
    }

    @Test(expected = UserExistException.class)
    public void createFail() {
        User user = getUser("example@mail.com");
        userDao.create(user);
    }

    @Test
    public void findById() {
        User user = userDao.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        assertNotNull(users);
    }

    @Test
    public void update() {
        User user = getUser("example@mail.com");
        user.setId(1L);
        userDao.update(user);
        Optional<User> updatedUser = userDao.findByEmailAndPassword("example@mail.com", "newUsersPass");
        assertTrue(updatedUser.isPresent());
    }

    private User getUser(String s) {
        return new User.Builder()
                .email(s)
                .password("newUsersPass")
                .firstNameUA("новий")
                .firstNameEN("new")
                .secondNameUA("користувач")
                .secondNameEN("user")
                .userCash(BigDecimal.ZERO)
                .build();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        userDao.delete(1L);
    }
}