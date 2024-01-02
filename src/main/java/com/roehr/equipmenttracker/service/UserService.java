package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.User;
import java.util.List;

public interface UserService {
    boolean authenticateUser(String username, String providedPin);
    User getUserByUsername(String username);
    List<User> getAllUsers();

    User findById(Long userId);

    void logout();
}
