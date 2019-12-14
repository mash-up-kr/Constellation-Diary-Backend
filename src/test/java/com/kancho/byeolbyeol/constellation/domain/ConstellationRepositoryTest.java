package com.kancho.byeolbyeol.constellation.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ConstellationRepositoryTest {

    @Autowired
    ConstellationRepository constellationRepository;

    @Test
    public void 물병자리_조회하기() {
        List<Constellation> constellationList = constellationRepository.findAll();
        Constellation aquarius = constellationList.get(0);

        assertThat(aquarius.getId(), is(1));
        assertThat(aquarius.getName(), is("물병자리"));
        assertThat(aquarius.getDate(), is("1월 20일~2월18일"));
        assertThat(aquarius.getDescription(), is("상식이나 인간관계에 얽매이지 않고 나아가는 자유로운 개혁자"));
    }
}