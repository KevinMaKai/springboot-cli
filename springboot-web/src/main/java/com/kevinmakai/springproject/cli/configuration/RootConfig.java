package com.kevinmakai.springproject.cli.configuration;

import com.kevinmakai.springproject.cli.common.utils.SpringContextHolder;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * RootConfig
 *
 * @author kevin
 * @date 2019-07-03 21:24
 */
@PropertySources(value = {
    @PropertySource(value = {"classpath:important.properties"}, encoding = "utf-8", ignoreResourceNotFound = true),
})
@Configuration
@EnableAspectJAutoProxy( proxyTargetClass = true)
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

}
