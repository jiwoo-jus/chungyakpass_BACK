package com.hanium.chungyakpassback.controller;


import com.hanium.chungyakpassback.dto.LoginDto;
import com.hanium.chungyakpassback.dto.TokenDto;
import com.hanium.chungyakpassback.jwt.JwtFilter;
import com.hanium.chungyakpassback.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        // LoginDto의 email, password를 파라미터로 받고 이를 이용해 UsernamePasswordAuthenticationToken을 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticationToken을 이용해서 Authentication객체를 생성하려고 authenticate 메소드를 실행하면 CustomUserDetailsService의 loadUserByUsername메소드가 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 그렇게 생성된 Authentication객체를 SecurityContext에 저장한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // createToken메소드를 통해 Authentication객체를 가지고 JWT Token을 생성한다.
        String jwt = tokenProvider.createToken(authentication);

        // JWT Token을 Response Header에도 넣어주고,
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // TokenDto를 이용해서 Response Body에도 넣어서 리턴한다.
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
