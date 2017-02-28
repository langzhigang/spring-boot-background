package cn.lzg.springboot.background.demo.service.impl;

import cn.lzg.springboot.background.Application;
import cn.lzg.springboot.background.demo.dao.UserMapper;
import cn.lzg.springboot.background.demo.domain.User;
import cn.lzg.springboot.background.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired
    private UserService userService;

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

    /**
     * 测试事物
     */
    @Test
    public void testTransaction(){
        userService.createByMyBatis(new User("事物",1));
    }

    /**
     * 测试缓存
     */
    @Test
    public void testCache(){
        User u = userService.createByMyBatis(new User("缓存create", 1));
        Long id = u.getId();
        User u1 = userService.getById(id);
        u1.setName("修改了缓存");
        userService.update(u1);
        User u2 = userService.getById(id);

        Assert.assertEquals(u1.getName(),u2.getName());
    }

    /**
     * 测试mybatis分页插件
     */
    @Test
    public void testPagePluges(){
        PageHelper.startPage(2, 3);
        List<User> users = userMapper.getAllUsersByMyBatis();
        System.out.println("Total: " + ((Page) users).getTotal());
        for (User user : users) {
            System.out.println("Country Name: " + user.getName());
        }
    }
}