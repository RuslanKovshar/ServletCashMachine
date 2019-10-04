package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ruslan.kovshar.database.DBInitializer;
import ruslan.kovshar.model.entity.Buyer;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CheckServiceTest {

    private CheckService checkService;

    @BeforeClass
    public static void init() {
        DBInitializer.initializeDatabase();
    }

    @Before
    public void setUp() {
        checkService = CheckService.getInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(CheckService.getInstance());
    }

    @Test
    public void createCheck() {
        Check check = new Check();
        check.setUser(new User.Builder().id(1L).build());
        Buyer buyer = new Buyer();
        buyer.setId(2L);
        check.setBuyer(buyer);
        check.setTotalPrice(BigDecimal.valueOf(100));
        checkService.createCheck(check);
    }

    @Test
    public void getAllUserChecks() {
        List<Check> allUserChecks = checkService.getAllUserChecks(new User.Builder().id(2L).build());
        assertNotNull(allUserChecks);
        assertEquals(allUserChecks.size(), 1);
    }

    @Test
    public void deleteCheck() {
        Check check = new Check();
        Buyer buyer = new Buyer();
        buyer.setId(1L);
        check.setBuyer(buyer);
        check.setId(1L);
        checkService.deleteCheck(check);
    }

    @Test
    public void findCheckById() {
        Check check = checkService.findCheckById(2L);
        assertNotNull(check);
    }

    @Test
    public void findPageWithChecks() {
        Page pageInfo = new Page(1, 1, "ASC");
        Page<Check> page = checkService.findPageWithChecks(new User.Builder().id(2L).build(), pageInfo);
        assertNotNull(page);
        assertEquals(page.getContent().size(), pageInfo.getMaxResult());
    }
}