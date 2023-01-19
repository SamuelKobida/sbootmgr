package com.example.blog.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("existujuci email");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("pouzivatel s ID: " + userId + "nexistuje");
        }
        userRepository.deleteById(userId);
    }


    public void updateUser(User newUser,Long userId) {
        userRepository.findById(userId)
            .map(user -> {
                user.setFirst_name(newUser.getFirst_name());
                user.setLast_name(newUser.getLast_name());
                user.setEmail(newUser.getEmail());
                user.setPhone_number(newUser.getPhone_number());
                return userRepository.save(user);
            })
            .orElseGet(() -> {
                return userRepository.save(newUser);
            });
        }
        }
