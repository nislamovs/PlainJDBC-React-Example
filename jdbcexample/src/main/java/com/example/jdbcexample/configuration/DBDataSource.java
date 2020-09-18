package com.example.jdbcexample.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "db")
@Setter
public class DBDataSource {

    private String url;
    private String driver;
    private String username;
    private String password;

    @Bean
    @Primary
    @Profile({"!c3p0", "!hikari"})
    public DataSource dbcpDataSource() {
        System.out.println("this is loaded!!!");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(20);

        return dataSource;
    }

//    @Bean
//    @Primary
//    @Profile({"!dbcp", "c3p0", "!hikari"})
//    public BasicDataSource c3p0DataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driver);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDefaultAutoCommit(false);
//        dataSource.setMinIdle(5);
//        dataSource.setMaxIdle(10);
//        dataSource.setMaxOpenPreparedStatements(20);
//
//        return dataSource;
//    }
//
//    @Bean
//    @Primary
//    @Profile({"!dbcp", "!c3p0", "hikari"})
//    public HikariDataSource hikariDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl(url);
//        dataSource.setDriverClassName(driver);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setAutoCommit(false);
////        dataSource.setMinimumIdle(5);
//        dataSource.setIdleTimeout(10000);
//        dataSource.setMaximumPoolSize(20);
//
//        return dataSource;
//    }
}