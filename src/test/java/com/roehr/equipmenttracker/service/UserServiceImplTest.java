package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.User;
import com.roehr.equipmenttracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticateUser_whenValidCredentials_thenAuthenticate() {
        String username = "testUser";
        String pin = "1234";
        String hashedPin = "$2a$12$4snQoeQaaXmqDi8usBUBVOSuVRicZ8w/Lx.SAnm94VWm3AbqMIP0q";

        User user = new User(username, hashedPin);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(pin, hashedPin)).thenReturn(true);

        boolean isAuthenticated = userService.authenticateUser(username, pin);

        assertTrue(isAuthenticated);
    }

    @Test
    public void authenticateUser_whenInvalidCredentials_thenDoNotAuthenticate() {
        String username = "testUser";
        String pin = "1234";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        boolean isAuthenticated = userService.authenticateUser(username, pin);

        assertFalse(isAuthenticated);
    }

    @Test
    public void getUserByUsername_whenUserExists_thenReturnUser() {
        String username = "testUser";
        User user = new User(username, "hashedPin");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User result = userService.getUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void getUserByUsername_whenUserDoesNotExist_thenReturnNull() {
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User result = userService.getUserByUsername(username);

        assertNull(result);
    }

    @Test
    public void getAllUsers_whenCalled_thenReturnListOfUsers() {
        List<User> users = Arrays.asList(new User("user1", "pin1"), new User("user2", "pin2"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
