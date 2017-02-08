package cn.lzg.springboot.background.demo.domain;

import java.util.Date;

/**
 * @author lzg
 * @Date 2017/2/3.
 */
public class User {

    private Long id;
    private String name;
    private Integer age;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
