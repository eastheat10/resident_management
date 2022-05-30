package com.nhnacademy.jpa.config;

import com.nhnacademy.jpa.Base;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
//        dataSource.setUrl("jdbc:mysql://133.186.211.156:3306/nhn_academy_17");
//        dataSource.setUsername("nhn_academy_17");
//        dataSource.setPassword("e(kbT!u88]ReeHwL");

        dataSource.setUrl("jdbc:mysql://localhost:3306/resident");
        dataSource.setUsername("root");
        dataSource.setPassword("Ehdduf97^^");

        dataSource.setInitialSize(2);
        dataSource.setMaxTotal(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxIdle(2);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }
}
