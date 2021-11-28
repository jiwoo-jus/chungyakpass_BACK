package com.hanium.chungyakpassback.controller;


import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.dto.input.UserDto;
import com.hanium.chungyakpassback.service.authority.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // UserDto객체를 파라미터로 받아서 UserService의 signup메소드를 호출한다.
    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }
}
