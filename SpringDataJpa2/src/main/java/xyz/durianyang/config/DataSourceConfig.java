package xyz.durianyang.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author Durian
 * @date 2019-09-24 20:53
 */
@Configuration
@PropertySource(value = "classpath:db.properties")
public class DataSourceConfig
{
    @Autowired
    private Environment en;

    @Bean
    public DataSource dataSource() throws PropertyVetoException
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(en.getProperty("driverClass"));
        dataSource.setJdbcUrl(en.getProperty("jdbcUrl"));
        dataSource.setUser(en.getProperty("user"));
        dataSource.setPassword(en.getProperty("password"));
        return dataSource;
    }
}
