package com.kevinmakai.springproject.cli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请填写类的描述
 *
 * @author makai5
 * @date 2020-05-14 11:36
 */
@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String hello(){
        return "helloworld muxin~~";
    }
}
