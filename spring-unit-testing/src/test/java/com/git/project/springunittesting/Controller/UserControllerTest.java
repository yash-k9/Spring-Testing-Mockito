package com.git.project.springunittesting.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.project.springunittesting.Model.User;
import com.git.project.springunittesting.Service.UserService;
import org.hibernate.cfg.Environment;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void home() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello User"));
    }


    @Test
    void testDeleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAll"))
                .andExpect(status().isOk());
        verify(userService).deleteAll();
    }

    @Test
    void testDeleteByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1"))
                .andExpect(status().isOk());
        verify(userService).deleteById(1);
    }

    @Test
    void testAddUser() throws Exception{
        String json = "{\"name\":\"Ajay\",\"position\":\"DevOps\",\"address\":\"hyd\"}";

        User user = new User(1, "Ajay", "DevOps", "hyd");

        mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Created"));

    }


    @Test
    void testGetUserById() throws Exception{
        when(userService.getUserByID(1)).thenReturn(java.util.Optional.of(new User(1, "Ajay", "Dev", "Hyd")));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk());

        verify(userService).getUserByID(1);
    }





}