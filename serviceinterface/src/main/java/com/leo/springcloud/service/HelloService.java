package com.leo.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable("name") String name);

    @RequestMapping(path = "/hello/v2/{name}", method = RequestMethod.GET)
    String hellov2(@PathVariable("name") String name);

    @RequestMapping(path = "/hello/v3/{id}", method = RequestMethod.GET)
    Integer hellov3(@PathVariable("id") Integer id);
}
