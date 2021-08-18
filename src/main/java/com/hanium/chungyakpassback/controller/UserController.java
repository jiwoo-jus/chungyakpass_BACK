package com.hanium.chungyakpassback.controller;


import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.dto.UserDto;
import com.hanium.chungyakpassback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
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

    // @PreAuthorize를 통해서 USER, ADMIN 두 가지 권한 모두 허용
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    // @PreAuthorize를 통해서 ADMIN 권한만 호출 가능
    // UserService에서 만들었던 username 파라미터를 기준으로 유저정보와 권한정보를 리턴하는 api이다.
    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
    }
}
