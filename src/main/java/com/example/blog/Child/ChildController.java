package com.example.blog.Child;

import com.example.blog.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@RestController
public class ChildController {

    @Autowired
    private ChildRepository ChildRepository;
    @Autowired
    private UserRepository UserRepository;


    @GetMapping(path="/user/{user_id}/children")
    public List<Child> getChildren(
            @PathVariable(name="user_id") long userId
    ){
        List<Child> list =  ChildRepository.findAllByUser_Id(userId);
        return list;
    }

    @PostMapping("/user/{user_id}/child/create")
    public String saveChild(
            @RequestBody Child newChild,
            @PathVariable(name="user_id") long userId
    ){
        UserRepository.findById(userId).ifPresentOrElse(
                user -> {
                    newChild.setUser(user);
                    ChildRepository.save(newChild);
                },
                () -> {
                    throw new RuntimeException("Používatel sa nenašiel");
                }
        );
        return "Potomok vytvorený";
    }



    @DeleteMapping(path="user/{user_id}/children/delete")
    public String deleteChild(
            @PathVariable(name="user_id") long userId
    ){
        ChildRepository.deleteAllByUser_id(userId);
        return "Potomok bol vymazany";
    }

}





