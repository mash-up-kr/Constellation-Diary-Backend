package com.kancho.byeolbyeol.user.domain.user;

import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저_저장하고_불러오기() {
        //given
        userRepository.save(User.builder()
                .userId("test")
                .password("test123")
                .constellationsId(1L)
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getUserId(), is("test"));
        assertThat(user.getConstellationsId(), is(1L));
    }
}