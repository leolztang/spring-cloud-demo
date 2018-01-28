package com.leo.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${sys.user.name}")
    private String name;

    @RequestMapping(value = "/hi", method= RequestMethod.GET)
    public String hi(){
        return "hi "+name;
    }
}
