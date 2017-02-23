package cn.lzg.springboot.background;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 * @Author lzg
 * @Date 2017/1/24 23:27
 */
@SpringBootApplication
@EnableScheduling //启用定时任务的配置(@Scheduled)
@EnableAsync    //启动支持异步调用的配置
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        log.debug("debug ==================================={}",log.isDebugEnabled());
        log.info("info ===================================={}",log.isInfoEnabled());
        log.error("error==================================={}",log.isErrorEnabled());
    }
}
