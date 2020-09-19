package com.example.jdbcexample.configuration.datasourceConfigurations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "db")
@Setter
@Profile("hikari")
public class HikariCPDataSource {

    private String url;
    private String driver;
    private String username;
    private String password;

    @Bean(name = "hikari")
    public DataSource hikariDataSource() {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setAutoCommit(false);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(10000);
        dataSource.setMaximumPoolSize(20);

        return dataSource;
    }
}