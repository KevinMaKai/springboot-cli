package com.kevinmakai.springproject.cli;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

import java.lang.annotation.Annotation;

/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-02-10 16:17
 */
@SpringBootApplication(scanBasePackages = {"com.kevinmakai.springproject.cli"},
        scanBasePackageClasses = SpringBootBaseCliStartApplication.class, exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.kevinmakai.springproject.cli.dao.mapper")
//@ComponentScan(basePackages = "com.kevinmakai.springproject.cli.muxin")
public class SpringBootBaseCliStartApplication extends SpringBootServletInitializer {


    /**
     * 兼容tomcat部署模式
     *
     * @param application spring环境
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootBaseCliStartApplication.class);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseCliStartApplication.class,args);
        try {
            Class clazz = Class.forName("com.kevinmakai.springproject.cli.dao.mapper.UsersMapper");
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : annotations
                 ) {
                System.out.println(annotation.annotationType());

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("/**********************SPRINGBOOT-BASE-CLI-STARTED****************************/");

    }
}
