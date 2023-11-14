package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.LoginRequestDto;
import com.sparta.todoapp.dto.SignupRequestDto;
import com.sparta.todoapp.dto.UserInfoDto;
import com.sparta.todoapp.entity.UserRoleEnum;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void signup(SignupRequestDto signupRequestDto) {
        try {
            userService.signup(signupRequestDto);
        } catch (Exception e) {
            log.error("회원가입을 다시 해주세요.");
        }
    }

    @PostMapping("/user/login")
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        try {
            userService.login(loginRequestDto, res);
        } catch (Exception e) {
            log.error("아이디/비밀번호를 확인해주세요.");
        }
    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }
}