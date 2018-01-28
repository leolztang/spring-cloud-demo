package com.leo.springcloud.client;

import com.leo.springcloud.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloFeignClient {

    @Autowired
    HelloService service;

    @RequestMapping(value="hello/{name}",method= RequestMethod.GET )
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String feignHello(@PathVariable String name){
        return service.hello(name);
    }

    @RequestMapping(value="hello/v2/{name}",method= RequestMethod.GET )
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String feignHelloV2(@PathVariable String name){
        return service.hellov2(name);
    }

    @RequestMapping(value="hello/v3/{id}",method= RequestMethod.GET )
    @HystrixCommand(fallbackMethod = "helloFallback")
    public Integer feignHelloV2(@PathVariable Integer id){
        return service.hellov3(id);
    }

    public String helloFallback(String name){
       return "依赖服务调用失败";
    }

}
