//package com.roehr.equipmenttracker.security;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SecurityConfigTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void accessPublicEndpoint() throws Exception {
//        mockMvc.perform(get("/api/public/test"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username="user", authorities={"USER"})
//    public void accessProtectedEndpointWithUser() throws Exception {
//        mockMvc.perform(get("/api/resource/test"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void accessProtectedEndpointUnauthenticated() throws Exception {
//        mockMvc.perform(get("/api/resource/test"))
//                .andExpect(status().isUnauthorized());
//    }
//
//
//}
