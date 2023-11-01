package org.example.mywebapp.services;

import org.example.mywebapp.models.User;
import org.example.mywebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        // Implement user registration logic
        userRepository.save(user);
    }

    public boolean loginUser(User user) {
        // Implement user login logic
        User dbUser = userRepository.findByUsername(user.getUsername());
        return dbUser != null && dbUser.getPassword().equals(user.getPassword());
    }
}
