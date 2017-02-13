package cn.lzg.springboot.background.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置mybatis
 * @author lzg
 * @Date 2017/2/7.
 */
@Configuration
@MapperScan("cn.lzg.springboot.background.demo.dao")
public class MybatisConfig {

    @Bean("sqlSessionFactoryBean")
    @Primary
    @ConfigurationProperties(prefix="mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("primaryDataSource") DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean("sqlSessionFactory")
    @ConfigurationProperties(prefix="mybatis")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("sqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject();
    }
}
