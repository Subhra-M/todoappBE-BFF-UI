package com.security.todoDemo.repositiories;

import com.security.todoDemo.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByNameTest(){
        User user = new User();
        user.setName("TestUser");
        user.setCreatedDate(new Date());
        User result = entityManager.persistAndFlush(user);
        assertThat(userRepository.findByName(user.getName()).get().getName()).isEqualTo(result.getName());
    }

    @Test
    public void getOneTest(){
        User user = new User();
        user.setName("TestUser");
        user.setCreatedDate(new Date());
        User result = entityManager.persistAndFlush(user);
        assertThat(userRepository.getOne(result.getId()).getName()).isEqualTo(user.getName());
    }
}
