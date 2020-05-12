package com.kevinmakai.springproject.cli.configuration.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * DruidDataSourceConfig
 *
 * @author kevin
 * @date 2020-03-06 12:33
 */
@Configuration
@MapperScan(basePackages = "com.kevinmakai.springproject.cli.dao")
public class MybatisConfiguration {

}
