package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.response.DefaultRes;
import com.hanium.chungyakpassback.response.ResponseMessage;
import com.hanium.chungyakpassback.response.StatusCode;
import com.hanium.chungyakpassback.service.input.UserDataService;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserDataController {
    private final UserRepository userRepository;
    private final UserDataService userDataService;

    public UserDataController(UserRepository userRepository, UserDataService userDataService) {
        this.userRepository = userRepository;
        this.userDataService = userDataService;
    }


    @PostMapping("/house")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity house(@RequestBody HouseDto houseDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        userDataService.house(user, houseDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PatchMapping("/house")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity patchHouse(@RequestBody HouseDto houseDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        userDataService.patchHouse(user, houseDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
    }

    @PostMapping("/bankbook")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity userBankbook(@RequestBody UserBankbookDto userBankbookDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        userDataService.userBankbook(user, userBankbookDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/house/member")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMember(@RequestBody HouseMemberDto houseMemberDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        userDataService.houseMember(user, houseMemberDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/house/member/property")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){
        userDataService.houseMemberProperty(houseMemberPropertyDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/house/member/chungyak")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberChungyak(@RequestBody HouseMemberChungyakDto houseMemberChungyakDto){
        userDataService.houseMemberChungyak(houseMemberChungyakDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PostMapping("/house/member/chungyak/restriction")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseMemberChungyakRestriction(@RequestBody HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){
        userDataService.houseMemberChungyakRestriction(houseMemberChungyakRestrictionDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_DATA), HttpStatus.OK);
    }

    @PatchMapping("/house/holder")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity houseHolder(@RequestBody HouseHolderDto houseHolderDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        userDataService.houseHolder(user, houseHolderDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
    }

}
