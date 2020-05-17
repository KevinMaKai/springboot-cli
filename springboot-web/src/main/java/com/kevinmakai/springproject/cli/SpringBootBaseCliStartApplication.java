package com.kevinmakai.springproject.cli;


import com.kevinmakai.springproject.cli.common.utils.SpringContextHolder;
import com.kevinmakai.springproject.cli.dao.domain.Users;
import com.kevinmakai.springproject.cli.mapper.UsersMapper;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-02-10 16:17
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.kevinmakai.springproject.cli"},
        scanBasePackageClasses = SpringBootBaseCliStartApplication.class, exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.kevinmakai.springproject.cli.mapper")
//@ComponentScan(basePackages = "com.kevinmakai.springproject.cli")
public class SpringBootBaseCliStartApplication extends SpringBootServletInitializer {


    /**
     * 兼容tomcat部署模式
     *
     * @param application spring环境
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        MDC.put();
        return application.sources(SpringBootBaseCliStartApplication.class);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz1 = Class.forName("com.mysql.cj.jdbc.Driver");

        SpringApplication.run(SpringBootBaseCliStartApplication.class,args);
        try {
            Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
            String canonicalName = clazz.getCanonicalName();
//            for (Annotation annotation : annotations
//                 ) {
//                System.out.println(annotation.annotationType());
//
//            }
            System.out.println(canonicalName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("/**********************SPRINGBOOT-BASE-CLI-STARTED****************************/");
//        System.out.println(SpringContextHolder.getBean("myMapper").toString());
        UsersMapper usersMapper = SpringContextHolder.getBean("usersMapper");
        Users users = new Users();
        users.setCid("testcid");
        users.setFaceImage("faceImage");
        users.setFaceImageBig("faceBigImage");
        users.setId("12344");
        users.setNickname("test");
        users.setPassword("password");
        users.setQrcode("code");
        users.setUsername("haode");
        usersMapper.insert(users);
    }
}
