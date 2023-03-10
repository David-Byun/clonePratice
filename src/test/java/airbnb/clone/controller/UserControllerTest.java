package airbnb.clone.controller;

import airbnb.clone.model.User;
import airbnb.clone.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMybatis
//스프링 모든 빈 로드하여 테스트하는 방식 : @SpringBootTest
//해당 클래스만 실제 로드하여 테스트
@WebMvcTest(UserController.class)
class UserControllerTest {

    //컨트롤러 api 테스트 하는 용도인 MockMvc 객체 주입
    //perform(httpMethod)로 실행하며 andExpect, andDo, andReturn 확인
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("유저 전체조회 테스트")
    void getUserListTest() throws Exception {
        List<User> users = new ArrayList<>();

        //given
        users.add(User.builder().name("John").email("aaa@naver.com").birthDay("20080302").phoneNumber("010-2332-3534").build());

        //when
        given(userService.findAllUser()).willReturn(users);

        //then
        mvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")));
    }

    @Test
    @DisplayName("유저 개별조회 테스트")
    void getTest() throws Exception {
        //given
        User user1 = User.builder().userId(1).
                name("John1").email("aaa@naver.com").birthDay("20080302").phoneNumber("010-2232-3434").build();

        //when
        given(userService.findUserInfo(1)).willReturn(Optional.ofNullable(user1));


        //then
        mvc.perform(get("/api/user/1")).andExpect(status().isOk()).andExpect(content().string(containsString("John1")));
    }


    @Test
    @DisplayName("유저 등록 테스트")
    void testSignUp() throws Exception {
        //given
        User user = User.builder().userId(1).
        name("John").email("aaa@naver.com").birthDay("20080302").phoneNumber("010-2232-3434").build();
        Gson gson = new Gson();
        String content = gson.toJson(user);

        //when
        mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                .content(content)).andExpect(status().isCreated());

        //then
        verify(userService, times(1)).signUp(refEq(user));
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void testSignUp() throws Exception {
        //given
        User user = User.builder().userId(1).
                name("John").email("aaa@naver.com").birthDay("20080302").phoneNumber("010-2232-3434").build();
        Gson gson = new Gson();
        String content = gson.toJson(user);

        //when
        mvc.perform(post("/api/user/1/delete").contentType(MediaType.APPLICATION_JSON)
                .content(content)).andExpect(status().isOk());

        //then
        verify(userService, times(1)).signUp(refEq(user));
    }


}