package com.sparta.todoapp.dto;

import lombok.Getter;

@Getter
public class ToDoRequestDto {
    private String title;
    private String username;
    private String password;
    private String contents;
}
