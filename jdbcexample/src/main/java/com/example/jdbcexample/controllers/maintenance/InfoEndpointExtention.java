package com.example.jdbcexample.controllers.maintenance;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
@RequiredArgsConstructor
public class InfoEndpointExtention {

    //To-know-which-connection-pool-is-used
    //Init sequence : Tomcat pooling, HikariCP, Commons DBCP and Commons DBCP2.

    private final InfoEndpoint delegate;
    private final DataSource dataSource;
    private final Environment environment;

    @ReadOperation
    public WebEndpointResponse<Map> info() {
        Map<String, Object> infoMap = new LinkedHashMap<>();

        infoMap.put("Datasource", dataSource.toString());
        infoMap.put("ActiveProfiles", this.environment.getActiveProfiles());

        Map<String, Object> info = this.delegate.info();
        infoMap.putAll(info);

        Integer status = getStatus(info);
        return new WebEndpointResponse<>(infoMap, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        // return 5xx if this is a snapshot
        return 200;
    }
}
