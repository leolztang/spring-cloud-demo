package com.leo.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloServiceConsumer {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(path = "helloConsumer/{name}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello(@PathVariable String name){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello/"+name,String.class).getBody();
    }

    public String helloFallback(String name){
        return "依赖服务调用失败";
    }
}
