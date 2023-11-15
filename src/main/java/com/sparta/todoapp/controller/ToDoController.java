package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.ToDoRequestDto;
import com.sparta.todoapp.dto.ToDoResponseDto;
import com.sparta.todoapp.entity.ToDo;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.ToDoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/todocards")
    public ToDoResponseDto createToDoCard(
            @RequestBody ToDoRequestDto toDoRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return toDoService.createToDoCard(toDoRequestDto, userDetails.getUser());
    }

    @GetMapping("/todocards")
    public List<ToDoResponseDto> getToDoCards() {
        return toDoService.getToDoCards();
    }

    @PutMapping("/todocards/{id}")
    public Long updateToDoCard(@PathVariable Long id, @RequestBody ToDoRequestDto toDoRequestDto) {
        return toDoService.updateToDoCard(id, toDoRequestDto);
    }

    @DeleteMapping("/todocards/{id}")
    public Long deleteToDoCard(@PathVariable Long id) {
        return toDoService.deleteToDoCard(id);
    }
}
