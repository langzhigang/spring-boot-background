package cn.lzg.springboot.background.demo.service.impl;

import cn.lzg.springboot.background.Application;
import cn.lzg.springboot.background.demo.dao.UserMapper;
import cn.lzg.springboot.background.demo.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试jdbcTemplate
 *
 * @author lzg
 * @Date 2017/2/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Autowired
    protected UserMapper userMapper;

    @Before
    public void setUp() {
//        jdbcTemplate1.update("DELETE  FROM  user ");
//        jdbcTemplate2.update("DELETE  FROM  user ");
    }

    /**
     * 测试CRUD
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        jdbcTemplate1.update("DELETE  FROM  user ");
        jdbcTemplate2.update("DELETE  FROM  user ");

        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

    }

    /**
     * 测试mybatis
     */
    @Test
    public void testMybatis() {
        User user = userMapper.findById(1);
        System.err.println(user.getCreateTime());
    }
}