package com.roehr.equipmenttracker.service;

import com.roehr.equipmenttracker.model.User;
import com.roehr.equipmenttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticateUser(String username, String providedPin) {
        logger.info("Authenticating user: {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(providedPin, user.getHashedPin());
            logger.info("User found. Credentials match: {}", isMatch);
            return isMatch;
        }
        logger.warn("User '{}' not found.", username);
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void logout() {
    }


}
