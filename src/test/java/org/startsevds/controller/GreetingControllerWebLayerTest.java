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
public class GreetingControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserJpaRepository userJpaRepository;

    @Test
    public void shouldReturnActualName() throws Exception{
        mockMvc.perform(get("/greeting/Dmitrii")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("Guten Tag Herr Dmitrii!")));
    }

    @Test
    public void shouldReturnMasterName() throws Exception{
        String masterName = "Van Darkholme";

        when(userJpaRepository.findByName(masterName)).thenReturn(new UserEntity(1L,masterName));

        mockMvc.perform(get("/greeting/master")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("Guten Tag Herr Van Darkholme!")));
    }
}
