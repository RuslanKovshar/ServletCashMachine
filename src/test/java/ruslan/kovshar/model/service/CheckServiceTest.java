package ruslan.kovshar.model.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CheckServiceTest {

    @Mock
    private CheckService checkService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUserChecks() {
        List<Check> presentChecks = new ArrayList<>();
        presentChecks.add(new Check());
        User user = new User.Builder().id(2L).build();
        Mockito.when(checkService.getAllUserChecks(user))
                .thenReturn(presentChecks)
                .thenReturn(Collections.emptyList())
                .thenReturn(null);

        assertEquals(checkService.getAllUserChecks(user), presentChecks);
        assertEquals(checkService.getAllUserChecks(user), Collections.emptyList());
        assertNull(checkService.getAllUserChecks(user));
    }

    @Test
    public void findCheckById() {
        Check check = new Check();
        check.setId(1L);
        Mockito.when(checkService.findCheckById(1L))
                .thenReturn(check);
        assertNotNull(check);
        assertEquals(check.getId().intValue(),1L);
    }

    @Test
    public void findPageWithChecks() {
        Page pageInfo = new Page(1, 1, "ASC");
        User user = new User.Builder().id(2L).build();
        Page<Check> page = new Page<>();

        Mockito.when(checkService.findPageWithChecks(user,pageInfo))
                .thenReturn(page);

        assertNotNull(checkService.findPageWithChecks(user,pageInfo));
        assertEquals(checkService.findPageWithChecks(user,pageInfo),page);
    }
}