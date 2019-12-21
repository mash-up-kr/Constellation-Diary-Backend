package com.kancho.byeolbyeol.constellation.web;

import com.kancho.byeolbyeol.constellation.service.ConstellationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConstellationController.class)
public class ConstellationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConstellationService constellationService;

    @Test
    public void 별자리_이미지URL_리스트_api() throws Exception {
        this.mockMvc.perform(get("/constellations/included-image"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}