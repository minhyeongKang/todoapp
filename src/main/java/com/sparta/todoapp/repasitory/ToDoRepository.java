package com.sparta.todoapp.repasitory;

import com.sparta.todoapp.entity.ToDo;
import com.sparta.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByUser(User user);
}
