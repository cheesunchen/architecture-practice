package com.cheesun.controller;

import com.cheesun.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author cheesun
 * @date 2020/11/13
 */
@ApiIgnore
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public JSONResult hello() {
        logger.info("info: hello~~");
        return JSONResult.ok("Hello World");
    }

    public static void main(String[] args) {

    }
}
