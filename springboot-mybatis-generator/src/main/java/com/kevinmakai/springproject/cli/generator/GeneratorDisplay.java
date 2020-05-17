package com.kevinmakai.springproject.cli.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-05-12 13:56
 */
public class GeneratorDisplay {

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //指定逆向工程配置文件
        File configFile = new File("springboot-mybatis-generator/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
        warnings.forEach(System.out::println);
    }

    public static void main(String[] args) {
        GeneratorDisplay generatorDisplay = new GeneratorDisplay();
        try {
            generatorDisplay.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
