package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.service.input.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @PostMapping("/house")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseResponseDto> house(@RequestBody HouseDto houseDto){

        return ResponseEntity.ok(userDataService.house(houseDto));
    }

    @PutMapping("/house/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long id, @RequestBody HouseUpdateDto houseUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouse(id, houseUpdateDto));
    }

    @DeleteMapping("/house/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouse(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouse(id));
    }


    @PostMapping("/bankbook")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> userBankbook(@RequestBody UserBankbookDto userBankbookDto){

        return ResponseEntity.ok(userDataService.userBankbook(userBankbookDto));
    }

    @PutMapping("/bankbook/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> updateUserBankbook(@PathVariable Long id, @RequestBody UserBankbookDto userBankbookDto){

        return ResponseEntity.ok(userDataService.updateUserBankbook(id, userBankbookDto));
    }

    @DeleteMapping("/bankbook/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteUserBankbook(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteUserBankbook(id));
    }


    @PostMapping("/house/member")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> houseMember(@RequestBody HouseMemberDto houseMemberDto){

        return ResponseEntity.ok(userDataService.houseMember(houseMemberDto));
    }

    @PutMapping("/house/member/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> updateHouseMember(@PathVariable Long id, @RequestBody HouseMemberUpdateDto houseMemberUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMember(id, houseMemberUpdateDto));
    }

    @DeleteMapping("/house/member/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMember(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouseMember(id));
    }


    @PatchMapping("/house/holder/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseHolderDto> houseHolder(@PathVariable Long id, @RequestBody HouseHolderDto houseHolderDto){

        return ResponseEntity.ok(userDataService.houseHolder(id, houseHolderDto));
    }

    @PostMapping("/house/member/property")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){

        return ResponseEntity.ok(userDataService.houseMemberProperty(houseMemberPropertyDto));
    }

    @PutMapping("/house/member/property/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> updateHouseMemberProperty(@PathVariable Long id, @RequestBody HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberProperty(id, houseMemberPropertyUpdateDto));
    }

    @DeleteMapping("/house/member/property/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberProperty(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouseMemberProperty(id));
    }

    @PostMapping("/house/member/chungyak")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> houseMemberChungyak(@RequestBody HouseMemberChungyakDto houseMemberChungyakDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyak(houseMemberChungyakDto));
    }

    @PutMapping("/house/member/chungyak/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> updateHouseMemberChungyak(@PathVariable Long id, @RequestBody HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyak(id, houseMemberChungyakUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyak(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyak(id));
    }

    @PostMapping("/house/member/chungyak/restriction")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> houseMemberChungyakRestriction(@RequestBody HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyakRestriction(houseMemberChungyakRestrictionDto));
    }

    @PutMapping("/house/member/chungyak/restriction/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> updateHouseMemberChungyakRestriction(@PathVariable Long id, @RequestBody HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyakRestriction(id, houseMemberChungyakRestrictionUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/restriction/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyakRestriction(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyakRestriction(id));
    }




//    @PatchMapping("/house")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity patchHouse(@RequestBody HouseDto houseDto){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        userDataService.patchHouse(user, houseDto);
//        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
//    }
}
