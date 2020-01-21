package com.kancho.byeolbyeol.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationNumberController.class)
public class SignUpNumberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationNumberService authenticationNumberService;

    @Test
    public void 회원가입_인증번호_생성_성공() throws Exception {
        ReqSignUpNumberDto reqSignUpNumberDto =
                new ReqSignUpNumberDto("test@naver.com");

        this.mockMvc.perform(post("/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqSignUpNumberDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void 인증번호_생성_요청_이메일이_누락될_경우() throws Exception {
        ReqSignUpNumberDto reqSignUpNumberDto =
                new ReqSignUpNumberDto();

        this.mockMvc.perform(post("/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqSignUpNumberDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 인증번호_인증_성공() throws Exception {
        ReqValidationSignUpNumberDto reqValidationSignUpNumberDto =
                new ReqValidationSignUpNumberDto(123456L, "test@naver.com");

        this.mockMvc.perform(post("/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqValidationSignUpNumberDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 인증번호_인증_요청시_쿼리_파라미터가_누락될_경우() throws Exception {
        ReqValidationSignUpNumberDto reqValidationSignUpNumberDto = new ReqValidationSignUpNumberDto();

        this.mockMvc.perform(post("/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqValidationSignUpNumberDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
