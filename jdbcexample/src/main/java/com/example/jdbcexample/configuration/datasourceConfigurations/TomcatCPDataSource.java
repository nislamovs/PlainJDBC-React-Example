package com.example.jdbcexample.configuration.datasourceConfigurations;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "db")
@Setter
@Profile("tomcat")
public class TomcatCPDataSource {

    private String url;
    private String driver;
    private String username;
    private String password;

    @Bean(name = "tomcat")
    public DataSource tomcatDataSource() {
        System.out.println("Tomcat connection pool is selected!");
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setMaxWait(20000);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setInitialSize(20);

        return dataSource;
    }
}