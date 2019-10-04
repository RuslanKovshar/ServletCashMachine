package ruslan.kovshar.model.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import static org.junit.Assert.*;

public class JDBCRoleDaoTest {

    private RoleDao roleDao;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        roleDao = daoFactory.createRoleDao();
    }

    @After
    public void close() {
        roleDao.close();
    }

    @Test
    public void setUserRole() {
        roleDao.setUserRole(new User.Builder().id(2L).build(), Roles.SENIOR_CASHIER);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void create() {
        roleDao.create(Roles.CASHIER);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById() {
        roleDao.findById(1L);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findAll() {
        roleDao.findAll();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        roleDao.update(Roles.CASHIER);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        roleDao.delete(3L);
    }

    @Test
    public void deleteUserRole() {
        roleDao.deleteUserRole(new User.Builder().id(1L).build());
    }
}