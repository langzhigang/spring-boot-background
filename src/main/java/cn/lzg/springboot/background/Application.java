package cn.lzg.springboot.background;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 *
 * @Author lzg
 * @Date 2017/1/24 23:27
 */
@SpringBootApplication
@EnableScheduling //启用定时任务的配置(@Scheduled)
@EnableAsync    //启动支持异步调用的配置
@EnableCaching  //启动缓存支持
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
