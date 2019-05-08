# spring-boot-background

##运行方式
>运行Application类中的main方法

>在浏览器中访问 localhost:8080/background/swagger-ui.html(就能看到swagger api的页面了)

>test文件夹下面有很多单元测试，在maven打包或者单独测试的时候可以运行

>除了resources下面有配置文件外，cn.lzg.springboot.background.config包下面有很多基于注解的配置类


##目前项目有的功能

	1.restful接口
	2.单元测试（mock junit）
	3.配置两个数据源
	4.整合jdbcTemplate
	5.整合mybatis
	6.整合redis
	7.整合swagger ui
	8.配置了多环境支持，可在打包后用 java -jar xxx.jar --spring.profiles.active=test 来指定运行的环境
	9.引入定时任务,demo见 cn.lzg.springboot.background.demo.task.ScheduledTasks
	10.引入异步调用(@Async),demo见 cn.lzg.springboot.background.demo.async.TaskTest
	11.定制化配置logback日志
	12.添加aop日志拦截，拦截web层请求。详情见 cn.lzg.springboot.background.demo.aspect.WebLogAspect
	13.添加spring 缓存配置(使用redis作为缓存)
	14.添加spring boot监控 localhost:8080/heath 能看到监控信息（更多信息看文档）
	15.添加分页插件
	    ```单元测试见：cn.lzg.springboot.background.demo.service.impl.UserServiceImplTest.testPagePluges()
	    ```插件详情：https://github.com/pagehelper/pagehelper-spring-boot
		
##后续跟新