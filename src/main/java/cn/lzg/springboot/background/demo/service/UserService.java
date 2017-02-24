package cn.lzg.springboot.background.demo.service;

import cn.lzg.springboot.background.demo.domain.User;

import java.util.List;

/**
 * @author lzg
 * @Date 2017/2/3.
 */
public interface UserService {

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

    /**
     * 新增用户通过Mybatis
     */
    User createByMyBatis(User user);

    /**
     * 获得用户总量通过Mybatis
     * @return
     */
    List<User> getAllUsersByMyBatis();

    /**
     * 删除所有用户通过Mybatis
     * @return
     */
    void deleteAllUsersByMyBatis();

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * 更新缓存
     * @param u1
     * @return
     */
    User update(User u1);
}
