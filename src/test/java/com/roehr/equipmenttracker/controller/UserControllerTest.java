package com.roehr.equipmenttracker.controller;

import com.roehr.equipmenttracker.model.User;
import com.roehr.equipmenttracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers_Success() throws Exception {
        given(userService.getAllUsers()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void authenticateUser_Success() throws Exception {
        given(userService.authenticateUser("user", "pin")).willReturn(true);

        mockMvc.perform(post("/api/users/authenticate")
                        .param("username", "user")
                        .param("pin", "pin"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByUsername_Success() throws Exception {
        given(userService.getUserByUsername("user")).willReturn(new User("user", "hashedPin"));

        mockMvc.perform(get("/api/users/username/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user"));
    }
}
