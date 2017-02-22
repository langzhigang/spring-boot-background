package cn.lzg.springboot.background.demo.controller;

import cn.lzg.springboot.background.demo.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试UserController
 *
 * @author lzg
 * @Date 2017/2/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class UserControllerTest {

    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    /**
     * 测试UserController CRUD
     *
     * @throws Exception
     */
    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request;

        // 1、get查一下user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        // 2、post提交一个user
        User user = new User();
        user.setId(1L);
        user.setName("测试新增");
        user.setAge(20);
        String userJson = objectMapper.writeValueAsString(user);

        request = post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3、get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[" + userJson + "]")));

        // 4、put修改id为1的user
        user.setId(1L);
        user.setName("测试修改");
        user.setAge(30);
        userJson = objectMapper.writeValueAsString(user);

        request = put("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get一个id为1的user
        request = get("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo(userJson)));

        // 6、del删除id为1的user
        request = delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }
}