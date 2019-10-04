package ruslan.kovshar.model.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DaoFactoryTest {

    @Test
    public void getInstance() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        assertNotNull(daoFactory);
    }
}