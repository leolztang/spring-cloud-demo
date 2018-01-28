package com.leo.springcloud.service;

import com.leo.springcloud.dao.DummyDao;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 *
 */
@RestController
@RefreshScope
public class HelloServiceImpl implements   HelloService {

    @Autowired
    DummyDao dao;
    @Value("${sys.user.name}")
    private String name;


    /**
     * 从配置中心读取的参数
     * @param name
     * @return
     */
    @Override
    @RequestMapping(path = "/hello/{name}",method = RequestMethod.GET)
    public String hello(@PathVariable String name) {
        return "hello " + name +",name from config is:"+this.name;
    }

    /**
     * 模拟超时
     * @param name
     * @return
     */
    @Override
    @RequestMapping(path = "/hello/v2/{name}",method = RequestMethod.GET)
    public String hellov2(@PathVariable String name) {
        try {
            int sleepTims = new Random().nextInt(3000);
            System.out.println(sleepTims);
            Thread.sleep(sleepTims);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello v2" + name;
    }
    @Override
    @RequestMapping(path="/hello/v3/{id}", method=RequestMethod.GET)
    public Integer hellov3(@PathVariable Integer id){
        return dao.find(id);
    }

}