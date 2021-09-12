package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
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
    public ResponseEntity<HouseResponseDto> house(@RequestBody HouseDto houseDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        return ResponseEntity.ok(userDataService.house(user, houseDto));
    }

    @PutMapping("/house/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long id, @RequestBody HouseUpdateDto houseUpdateDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        return ResponseEntity.ok(userDataService.updateHouse(id, user, houseUpdateDto));
    }


    @PostMapping("/bankbook")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> userBankbook(@RequestBody UserBankbookDto userBankbookDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        return ResponseEntity.ok(userDataService.userBankbook(user, userBankbookDto));
    }

    @PutMapping("/bankbook/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> updateUserBankbook(@PathVariable Long id, @RequestBody UserBankbookDto userBankbookDto){

        return ResponseEntity.ok(userDataService.updateUserBankbook(id, userBankbookDto));
    }


    @PostMapping("/house/member")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> houseMember(@RequestBody HouseMemberDto houseMemberDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        return ResponseEntity.ok(userDataService.houseMember(user, houseMemberDto));
    }

    @PutMapping("/house/member/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> updateHouseMember(@PathVariable Long id, @RequestBody HouseMemberUpdateDto houseMemberUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMember(id, houseMemberUpdateDto));
    }


    @PatchMapping("/house/holder/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseHolderDto> houseHolder(@PathVariable Long id, @RequestBody HouseHolderDto houseHolderDto){

        return ResponseEntity.ok(userDataService.houseHolder(id, houseHolderDto));
    }

    @PostMapping("/house/member/property")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){
        userDataService.houseMemberProperty(houseMemberPropertyDto);
        return ResponseEntity.ok(userDataService.houseMemberProperty(houseMemberPropertyDto));
    }

    @PutMapping("/house/member/property/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity updateHouseMemberProperty(@PathVariable Long id, @RequestBody HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberProperty(id, houseMemberPropertyUpdateDto));
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



//    @PatchMapping("/house")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity patchHouse(@RequestBody HouseDto houseDto){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        userDataService.patchHouse(user, houseDto);
//        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
//    }
}
