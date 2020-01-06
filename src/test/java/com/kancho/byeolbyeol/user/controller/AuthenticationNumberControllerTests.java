package com.kancho.byeolbyeol.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
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
public class AuthenticationNumberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationNumberService authenticationNumberService;

    @Test
    public void 회원가입_인증번호_생성_성공() throws Exception {
        ReqAuthenticationNumbersDto reqAuthenticationNumbersDto =
                new ReqAuthenticationNumbersDto("test@naver.com");

        this.mockMvc.perform(post("/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqAuthenticationNumbersDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void 인증번호_생성_요청_이메일이_누락될_경우() throws Exception {
        ReqAuthenticationNumbersDto reqAuthenticationNumbersDto =
                new ReqAuthenticationNumbersDto();

        this.mockMvc.perform(post("/authentication-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqAuthenticationNumbersDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 인증번호_인증_성공() throws Exception {
        ReqValidationNumberDto reqValidationNumberDto =
                new ReqValidationNumberDto(123456L, "test@naver.com");

        this.mockMvc.perform(post("/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqValidationNumberDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 인증번호_인증_요청시_쿼리_파라미터가_누락될_경우() throws Exception {
        ReqValidationNumberDto reqValidationNumberDto = new ReqValidationNumberDto();

        this.mockMvc.perform(post("/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqValidationNumberDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
