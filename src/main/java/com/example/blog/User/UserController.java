package com.example.blog.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Transactional
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;


    @GetMapping(path="/user")
    public List<User> getUsers() {
        List<User> list =  repository.findAll();

        return list;
    }

    @PostMapping(path="/user/create")
    public User saveUser(@RequestBody User user) {
        return repository.save(user);
    }


    @DeleteMapping(path="/user/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long userId) {
        repository.deleteById(userId);
        return "Pouzivatel vymazany";
    }

    @PutMapping(path="/user/{userId}/update")
    public User updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody User newUser)throws Exception {
        User user = repository.findById(userId)
                .orElseThrow(()-> new Exception("Pouzivatel s ID:"+userId+" sa nenasiel"));

        user.setFirst_name(newUser.getFirst_name());
        user.setLast_name(newUser.getLast_name());
        user.setEmail(newUser.getEmail());
        user.setPhone_number(newUser.getPhone_number());

        final User updatedUser = repository.save(user);
        return updatedUser;
    }

}





