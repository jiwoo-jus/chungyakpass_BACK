package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.*;
import com.hanium.chungyakpassback.entity.input.HouseMemberChungyakRestriction;
import com.hanium.chungyakpassback.response.DefaultRes;
import com.hanium.chungyakpassback.response.ResponseMessage;
import com.hanium.chungyakpassback.response.StatusCode;
import com.hanium.chungyakpassback.service.UserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("I'm doing test");
        return "hi";
    }

    @PostMapping("/house")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity house(@RequestBody HouseDto houseDto){
        userDataService.house(houseDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/userBankbook")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity userBankbook(@RequestBody UserBankbookDto userBankbookDto){
        userDataService.userBankbook(userBankbookDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/houseMember")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMember(@RequestBody HouseMemberDto houseMemberDto){
        userDataService.houseMember(houseMemberDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/houseMemberIncome")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberIncome(@RequestBody HouseMemberIncomeDto houseMemberIncomeDto){
        userDataService.houseMemberIncome(houseMemberIncomeDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/houseMemberProperty")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){
        userDataService.houseMemberProperty(houseMemberPropertyDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/houseMemberChungyak")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberChungyak(@RequestBody HouseMemberChungyakDto houseMemberChungyakDto){
        userDataService.houseMemberChungyak(houseMemberChungyakDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/houseMemberChungyakRestriction")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberChungyakRestriction(@RequestBody HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){
        userDataService.houseMemberChungyakRestriction(houseMemberChungyakRestrictionDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

}
