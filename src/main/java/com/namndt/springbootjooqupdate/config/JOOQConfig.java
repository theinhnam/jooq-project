package com.namndt.springbootjooqupdate.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JOOQConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public DefaultDSLContext defaultDSLContext(){
        return new DefaultDSLContext(dataSource, SQLDialect.POSTGRES);
    }
}
