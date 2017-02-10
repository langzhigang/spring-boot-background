package cn.lzg.springboot.background.demo;

import cn.lzg.springboot.background.Application;
import cn.lzg.springboot.background.demo.domain.User;
import cn.lzg.springboot.background.utils.protostuff.ProtoStuffSerializerUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lzg
 * @Date 2017/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testObj() throws Exception {
        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getName(), ProtoStuffSerializerUtil.serialize(user));
        User redisUser = ProtoStuffSerializerUtil
                .deserialize((byte[]) redisTemplate.opsForValue().get("超人"), User.class);
        Assert.assertEquals(20, redisUser.getAge().longValue());
    }
}