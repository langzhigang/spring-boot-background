package cn.lzg.springboot.background.demo.dao;

import cn.lzg.springboot.background.demo.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author lzg
 * @Date 2017/2/7.
 */
public interface UserMapper {

    User findById(@Param("id") Integer id);
}
