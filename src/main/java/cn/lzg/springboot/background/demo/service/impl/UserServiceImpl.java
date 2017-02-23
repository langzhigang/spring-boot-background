package cn.lzg.springboot.background.demo.service.impl;

import cn.lzg.springboot.background.demo.dao.UserMapper;
import cn.lzg.springboot.background.demo.domain.User;
import cn.lzg.springboot.background.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lzg
 * @Date 2017/2/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into user(name, age) values(?, ?)", name, age);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from user where name = ?", name);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from user", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from user");
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void createByMyBatis(User user) {
        user.setCreateTime(new Date());
        userMapper.create(user);
    }

}
