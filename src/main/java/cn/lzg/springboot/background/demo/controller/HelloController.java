package cn.lzg.springboot.background.demo.controller;

import cn.lzg.springboot.background.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

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

}
