package com.example.blog.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
         this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);

    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);

    }

    @PutMapping(path="{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody(required = false)User newUser
    ){
    userService.updateUser(newUser,userId);
    }
}





