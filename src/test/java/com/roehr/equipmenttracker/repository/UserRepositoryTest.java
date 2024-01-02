package com.roehr.equipmenttracker.repository;

import com.roehr.equipmenttracker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional  // Enable transaction management for the test
@Rollback  // Roll back transactions after each test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Disable auto-configuration of the test database
@ActiveProfiles("mysql") // Set the active profile to "mysql"
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserSaveAndRetrieve() {
        // Create a new user
        User newUser = new User("testUsername", "testPassword");

        // Save the user
        User savedUser = userRepository.save(newUser);

        // Retrieve the user
        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Assert that the retrieved user is not null and properties match
        assertNotNull(retrievedUser);
        assertEquals("testUsername", retrievedUser.getUsername());
        // The password is hashed, so a direct comparison is not possible in this case
    }
}