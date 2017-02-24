package cn.lzg.springboot.background.demo.service.impl;

import cn.lzg.springboot.background.demo.dao.UserMapper;
import cn.lzg.springboot.background.demo.domain.User;
import cn.lzg.springboot.background.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @CachePut(cacheNames = "user",key = "#user.id")
    public User createByMyBatis(User user) {
        user.setCreateTime(new Date());
        userMapper.create(user);
        return user;
    }

    @Override
    @Transactional(value = "primaryTransactionManager",readOnly = true)
    @Cacheable(cacheNames = "allUsers")
    public List<User> getAllUsersByMyBatis() {
        return userMapper.getAllUsersByMyBatis();
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    @CacheEvict(cacheNames = "allUsers")
    public void deleteAllUsersByMyBatis() {
        userMapper.deleteAllUsersByMyBatis();
    }

    @Override
    @Transactional(value = "primaryTransactionManager",readOnly = true)
    @Cacheable(cacheNames = "user",key = "#id")
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    @CachePut(cacheNames = "user",key = "#user.id")
    public User update(User user) {
        userMapper.update(user);
        return user;
    }
}
