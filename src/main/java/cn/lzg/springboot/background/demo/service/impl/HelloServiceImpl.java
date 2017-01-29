package cn.lzg.springboot.background.demo.service.impl;

import cn.lzg.springboot.background.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author lzg
 * @Date 2017/1/29 13:40
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String something) {
        return something;
    }
}
