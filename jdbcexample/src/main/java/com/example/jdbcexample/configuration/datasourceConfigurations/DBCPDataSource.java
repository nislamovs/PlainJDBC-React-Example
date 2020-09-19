package com.example.jdbcexample.configuration.datasourceConfigurations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.metadata.TomcatDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "db")
@Setter
@Profile("dbcp")
public class DBCPDataSource {

    private String url;
    private String driver;
    private String username;
    private String password;

    static {
        System.out.println("DBCP connection pool is selected!");
    }

    @Bean(name = "dbcp")
    public DataSource dbcpDataSource() {
        System.out.println("DBCP connection pool is selected!");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(20);

        return dataSource;
    }
}