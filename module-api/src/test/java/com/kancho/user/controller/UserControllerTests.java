package com.kancho.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancho.user.application.UserService;
import com.kancho.user.dto.requset.ReqSignUpDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 아이디_중복검사_성공() throws Exception {

        this.mockMvc.perform(get("/users/check")
                .contentType(MediaType.APPLICATION_JSON)
                .param("user-id", "testId"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_성공() throws Exception {
        ReqSignUpDto reqSignUpDto =
                new ReqSignUpDto("test@naver.com", "testId", "testpwd", "물병자리", "ㄹㅇㅁㄴㅁㄹㄴㅇ");

        this.mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "testToken")
                .header("Time-Zone", "KST")
                .content(objectMapper.writeValueAsString(reqSignUpDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_요청시_필드_누락시() throws Exception {
        ReqSignUpDto reqSignUpDto =
                new ReqSignUpDto();

        this.mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "testToken")
                .header("Time-Zone", "KST")
                .content(objectMapper.writeValueAsString(reqSignUpDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
