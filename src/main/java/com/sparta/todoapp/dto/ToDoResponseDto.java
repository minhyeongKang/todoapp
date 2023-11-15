package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.ToDo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ToDoResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public ToDoResponseDto(ToDo toDo) {
        this.id = toDo.getId();
        this.username = toDo.getUsername();
        this.title = toDo.getTitle();
        this.contents = toDo.getContents();
        this.createAt = toDo.getCreatedAt();
        this.modifiedAt = toDo.getModifiedAt();
    }
}
