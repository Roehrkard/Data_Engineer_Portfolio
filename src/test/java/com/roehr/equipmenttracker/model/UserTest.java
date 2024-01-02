package com.roehr.equipmenttracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUsernameGetter() {
        String username = "testUser";
        String pin = "1234";

        User user = new User(username, pin);

        assertEquals(username, user.getUsername(), "Username should match the one set in constructor");
    }

}
