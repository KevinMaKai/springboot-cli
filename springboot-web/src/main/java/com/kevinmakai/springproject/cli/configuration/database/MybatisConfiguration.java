package com.kevinmakai.springproject.cli.configuration.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * DruidDataSourceConfig
 *
 * @author kevin
 * @date 2020-03-06 12:33
 */
@Configuration
@MapperScan(basePackages = "com.kevinmakai.springproject.cli.dao")
public class MybatisConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(dataSourceTransactionManager(dataSource));
        transactionTemplate.setPropagationBehavior(0);
        return transactionTemplate;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
