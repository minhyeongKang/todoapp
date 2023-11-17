package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.ToDoResponseDto;
import com.sparta.todoapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/todocards")
    public List<ToDoResponseDto> getTotalCards() {
        return adminService.getTotalCards();
    }

    @DeleteMapping("/admin/todocards/{id}")
    public Long deleteToDoCard(@PathVariable Long id) {
        return adminService.adminDeleteCard(id);
    }

}
