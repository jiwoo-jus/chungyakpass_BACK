package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberProperty;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.input.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserDataController {
    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/house")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseReadDto> readHouses(){

        return ResponseEntity.ok(userDataService.readHouses());
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

    @GetMapping("/bankbook")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> readUserBankbooks(){

        return ResponseEntity.ok(userDataService.readUserBankbook());
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

    @GetMapping("/house/member/{houseId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<HouseMemberResponseDto>> readHouseMembers(@PathVariable Long houseId){

        return ResponseEntity.ok(userDataService.readHouseMembers(houseId));
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

    @PatchMapping("/house/member/homeless-start-date/{houseMemberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberHomelessStartDateDto> houseHolder(@PathVariable Long houseMemberId, @RequestBody HouseMemberHomelessStartDateDto houseMemberHomelessStartDateDto){
        return ResponseEntity.ok(userDataService.houseMemberHomelessStartDate(houseMemberId, houseMemberHomelessStartDateDto));
    }


    @PatchMapping("/house/holder/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseHolderDto> houseHolder(@PathVariable Long id, @RequestBody HouseHolderDto houseHolderDto){

        return ResponseEntity.ok(userDataService.houseHolder(id, houseHolderDto));
    }

    @GetMapping("/house/member/property/{houseMemberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<HouseMemberPropertyResponseDto>> readHouseMemberProperties(@PathVariable Long houseMemberId){

        return ResponseEntity.ok(userDataService.readHouseMemberProperties(houseMemberId));
    }


    @PostMapping("/house/member/property")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){

        return ResponseEntity.ok(userDataService.houseMemberProperty(houseMemberPropertyDto));
    }

    @PutMapping("/house/member/property/{houseMemberPropertyId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> updateHouseMemberProperty(@PathVariable Long houseMemberPropertyId, @RequestBody HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberProperty(houseMemberPropertyId, houseMemberPropertyUpdateDto));
    }

    @DeleteMapping("/house/member/property/{houseMemberPropertyId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberProperty(@PathVariable Long houseMemberPropertyId){

        return new ResponseEntity(userDataService.deleteHouseMemberProperty(houseMemberPropertyId));
    }

    @GetMapping("/house/member/chungyak/{houseMemberId}")
    public ResponseEntity<List<HouseMemberChungyakReadDto>> readHouseMemberChungyaks(@PathVariable Long houseMemberId){

        return ResponseEntity.ok(userDataService.readHouseMemberChungyaks(houseMemberId));
    }

//    @GetMapping("/house/member/chungyak/{id}")
//    public ResponseEntity<HouseMemberChungyakReadDto> readHouseMemberChungyak(@PathVariable Long id){
//
//        return ResponseEntity.ok(userDataService.readHouseMemberChungyak(id));
//    }

    @PostMapping("/house/member/chungyak")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> houseMemberChungyak(@RequestBody HouseMemberChungyakDto houseMemberChungyakDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyak(houseMemberChungyakDto));
    }

    @PutMapping("/house/member/chungyak/{houseMemberChungyakId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> updateHouseMemberChungyak(@PathVariable Long houseMemberChungyakId, @RequestBody HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyak(houseMemberChungyakId, houseMemberChungyakUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/{houseMemberChungyakId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyak(@PathVariable Long houseMemberChungyakId){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyak(houseMemberChungyakId));
    }

    @PostMapping("/house/member/chungyak/restriction")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> houseMemberChungyakRestriction(@RequestBody HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyakRestriction(houseMemberChungyakRestrictionDto));
    }

    @PutMapping("/house/member/chungyak/restriction/{houseMemberChungyakRestrictionId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> updateHouseMemberChungyakRestriction(@PathVariable Long houseMemberChungyakRestrictionId, @RequestBody HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyakRestriction(houseMemberChungyakRestrictionId, houseMemberChungyakRestrictionUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/restriction/{houseMemberChungyakRestrictionId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyakRestriction(@PathVariable Long houseMemberChungyakRestrictionId){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyakRestriction(houseMemberChungyakRestrictionId));
    }

}
