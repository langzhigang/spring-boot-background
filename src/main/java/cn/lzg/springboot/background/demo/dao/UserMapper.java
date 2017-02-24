package cn.lzg.springboot.background.demo.dao;

import cn.lzg.springboot.background.demo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lzg
 * @Date 2017/2/7.
 */
public interface UserMapper {

    User findById(@Param("id") Integer id);

    void create(User user);

    List<User> getAllUsersByMyBatis();

    int deleteAllUsersByMyBatis();

    User getById(@Param("id") Long id);

    void update(User user);
}
