package com.cheesun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther 太一
 * @date 2020/11/30
 * @time 15:30
 * @description
 */
@RestController
@RequestMapping("/hello/")
public class HelloController {

    @GetMapping("test")
    public String hello() {
        return "hello world";
    }
}
