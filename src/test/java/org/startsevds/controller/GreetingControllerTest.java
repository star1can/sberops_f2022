package org.startsevds.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.startsevds.entity.UserEntity;
import org.startsevds.repository.UserJpaRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GreetingControllerTest {

    @Autowired
    private GreetingController greetingController;

    @MockBean
    private UserJpaRepository userJpaRepository;

    @Test
    public void shouldReturnActualName() {
        String actual = greetingController.userGreeting("Dmitrii");
        Assert.assertEquals("Guten Tag Herr Dmitrii!", actual);
    }

    @Test
    public void shouldReturnMasterName() {
        String masterName = "Van Darkholme";

        when(userJpaRepository.findByName(masterName)).thenReturn(new UserEntity(1L, masterName));

        String actual = greetingController.masterGreeting();
        Assert.assertEquals("Guten Tag Herr Van Darkholme!", actual);
    }
}
