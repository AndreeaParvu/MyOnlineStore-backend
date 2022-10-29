package com.store.MyOnlineStore.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean
    public DataSource datasource(){
       return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/MyOnlineStore?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true&serverTimezone=UTC")
                .username("root")
                .password("password")
                .build();
    }
}
