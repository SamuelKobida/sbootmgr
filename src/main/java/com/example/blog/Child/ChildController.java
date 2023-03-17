package com.example.blog.Child;

import com.example.blog.User.User;
import com.example.blog.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/")
public class ChildController {

    @Autowired
    private ChildRepository ChildRepository;
    @Autowired
    private UserRepository UserRepository;


    @GetMapping(path="user/{user_id}/children")
    public List<Child> getChildren(
            @PathVariable(name="user_id") long userId
    ){
        List<Child> list =  ChildRepository.findAllByUser_Id(userId);
        return list;
    }


    @PostMapping("user/{user_id}/child/create")
    public String saveChild(
            @RequestBody Child newChild,
            @PathVariable(name="user_id") long userId
    ){
        User selectedUser = UserRepository.findById(userId).orElseThrow( /* throw your exception */);
        newChild.setUser( selectedUser );
        ChildRepository.save( newChild );
        return "Potomok vytvoreny";

    }


    @DeleteMapping(path="user/{user_id}/children/delete")
    public String deleteChild(
            @PathVariable(name="user_id") long userId
    ){

        ChildRepository.deleteAllByUser_id(userId);

        return "Potomok bol vymazany";
    }


//    @PutMapping(path="child/update/{childId}")
//    public Child updateChild(
//            @PathVariable("childId") Long childId,
//            @RequestBody Child newChild)throws Exception {
//        Child child = ChildRepository.findById(childId)
//                .orElseThrow(()-> new Exception("Child s ID:"+childId+" sa nenasiel"));
//
//        child.setDescription(newChild.getDescription());
//        child.setUser(newChild.getUser());
//
//        final Child updatedChild = ChildRepository.save(child);
//        return updatedChild;
//    }

}





