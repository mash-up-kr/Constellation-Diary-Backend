package com.kancho.byeolbyeol.constellation.service;

import com.kancho.byeolbyeol.constellation.dto.response.ResConstellationImageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstellationServiceTest {

    @Autowired
    private ConstellationService constellationService;

    @Test
    public void 별자리_이미지url_리스트_조회하기() {
        //when
        List<ResConstellationImageDto> resConstellationImageDtoList = constellationService.getConstellationImageList();
        ResConstellationImageDto aquarius = resConstellationImageDtoList.get(0);

        //then
        assertThat(resConstellationImageDtoList.size(), is(12));

        assertThat(aquarius.getId(), is(1));
        assertThat(aquarius.getName(), is("물병자리"));
        assertThat(aquarius.getDate(), is("1월 20일~2월 18일"));
        assertThat(aquarius.getDescription(), is("상식이나 인간관계에 얽매이지 않고 나아가는 자유로운 개혁자"));
    }
}