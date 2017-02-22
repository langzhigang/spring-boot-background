package cn.lzg.springboot.background.demo.controller;

import cn.lzg.springboot.background.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lzg
 * @Date 2017/1/24 23:08
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String index() {
        return helloService.say("hello");
    }

    @Value("${environment}")
    private String environment;

    @RequestMapping(value="/getEvn",method= RequestMethod.GET)
    public String getEnvironment() {
        return environment;
    }
}
