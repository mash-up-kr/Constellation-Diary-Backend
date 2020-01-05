package com.kancho.byeolbyeol.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancho.byeolbyeol.user.application.UserSignUpService;
import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserSignUpController.class)
class UserSignUpControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserSignUpService userSignUpService;

    @Test
    void 인증번호_생성_성공() throws Exception {
        ReqEmailDto reqEmailDto = new ReqEmailDto("test@naver.com");

        this.mockMvc.perform(post("/sign-up/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqEmailDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void 인증번호_생성_요청_이메일이_누락될_경우() throws Exception {
        ReqEmailDto reqEmailDto = new ReqEmailDto();

        this.mockMvc.perform(post("/sign-up/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqEmailDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void 인증번호_인증_성공() throws Exception {
        this.mockMvc.perform(get("/sign-up/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .param("authentication-number", "123456")
                .param("email", "test@naver.com"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 인증번호_인증_요청시_쿼리_파라미터가_누락될_경우() throws Exception {
        this.mockMvc.perform(get("/sign-up/authentication")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
