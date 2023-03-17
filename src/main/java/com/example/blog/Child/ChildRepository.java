package com.example.blog.Child;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChildRepository extends JpaRepository<Child,Long> {

    List<Child> findAllByUser_Id(long user_id);

    void deleteAllByUser_id(long usersToDelete);
}


