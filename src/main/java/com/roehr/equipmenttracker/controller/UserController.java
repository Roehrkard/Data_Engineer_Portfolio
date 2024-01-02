package com.roehr.equipmenttracker.controller;

import com.roehr.equipmenttracker.model.User;
import com.roehr.equipmenttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint for authenticating a user
//    @PostMapping("/authenticate")
//    public ResponseEntity<Boolean> authenticateUser(@RequestParam String username,
//                                                    @RequestParam String pin) {
//        boolean isAuthenticated = userService.authenticateUser(username, pin);
//        return isAuthenticated ? ResponseEntity.ok(true)
//                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//    }

    // Get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
