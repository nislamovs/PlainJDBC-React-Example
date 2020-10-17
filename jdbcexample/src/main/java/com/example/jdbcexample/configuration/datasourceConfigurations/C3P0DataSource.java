package com.example.jdbcexample.configuration.datasourceConfigurations;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ConfigurationProperties(prefix = "db")
@Setter
@Profile("c3p0")
public class C3P0DataSource {

    private String url;
    private String driver;
    private String username;
    private String password;

    @Bean(name = "c3p0")
    public DataSource c3p0DataSource() throws PropertyVetoException {
        System.out.println("c3p0 connection pool is selected!");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setJdbcUrl(url);
        dataSource.setDriverClass(driver);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setAutoCommitOnClose(true);
        dataSource.setMaxIdleTime(10);
        dataSource.setMaxStatements(20);

        return dataSource;
    }
}