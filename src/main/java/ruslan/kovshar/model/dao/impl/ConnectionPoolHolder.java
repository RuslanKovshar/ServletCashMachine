package ruslan.kovshar.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ruslan.kovshar.view.DatabaseProps.*;

/**
 * contains the data source
 */
class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null){
                    BasicDataSource ds = new BasicDataSource();

                    try (InputStream input = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {

                        Properties prop = new Properties();
                        prop.load(input);
                        ds.setUrl(prop.getProperty(DATABASE_URL));
                        ds.setUsername(prop.getProperty(DATABASE_USERNAME));
                        ds.setPassword(prop.getProperty(DATABASE_PASSWORD));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }
        return dataSource;
    }
}
