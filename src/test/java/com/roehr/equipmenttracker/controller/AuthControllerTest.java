package com.roehr.equipmenttracker.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.roehr.equipmenttracker.dto.LoginDto;
import com.roehr.equipmenttracker.service.UserService;
import com.roehr.equipmenttracker.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testAuthenticateUserSuccess() throws Exception {
        LoginDto loginDto = new LoginDto("user", "1234"); // Create a LoginDto object

        // Mock the userService to return true for authentication
        when(userService.authenticateUser(loginDto.getUsername(), loginDto.getProvidedPin())).thenReturn(true);

        // Mock the tokenService to return a token
        when(tokenService.createToken(loginDto.getUsername())).thenReturn("token");

        mockMvc.perform(post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user\",\"providedPin\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/api/logout"))
                .andExpect(status().isOk());
    }



}
