package cn.lzg.springboot.background.demo;

import cn.lzg.springboot.background.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试读取资源文件中的内容
 *
 * @author lzg
 * @Date 2017/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PropertiesTest {

    @Value("${cn.lzg.name}")
    private String name;

    @Value("${cn.lzg.desc}")
    private String desc;

    @Value("${environment}")
    private String environment;

    /**
     * 测试@Value注解读取
     */
    @Test
    public void test() {
        Assert.assertEquals(name, "郎志刚");
        Assert.assertEquals(desc, "name:郎志刚 age:25");
    }

    /**
     * 测试默认环境变量是否生效
     */
    @Test
    public void testEnv() {
        Assert.assertEquals(environment, "dev");
    }
}