package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberAdditionalInfo;
import com.hanium.chungyakpassback.entity.input.HouseMemberProperty;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.input.UserDataService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/house") //세대 조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseReadDto> readHouses(){

        return ResponseEntity.ok(userDataService.readHouses());
    }

    @PostMapping("/house") //세대 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseResponseDto> house(@RequestBody HouseDto houseDto){

        return ResponseEntity.ok(userDataService.house(houseDto));
    }

    @PutMapping("/house/{id}") //세대 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long id, @RequestBody HouseUpdateDto houseUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouse(id, houseUpdateDto));
    }

    @DeleteMapping("/house/{id}") //세대 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouse(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouse(id));
    }

    @GetMapping("/bankbook") //청약통장 조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> readUserBankbooks(){

        return ResponseEntity.ok(userDataService.readUserBankbook());
    }

    @PostMapping("/bankbook") //청약통장 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> userBankbook(@RequestBody UserBankbookDto userBankbookDto){

        return ResponseEntity.ok(userDataService.userBankbook(userBankbookDto));
    }

    @PutMapping("/bankbook/{id}") //청약통장 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserBankbookResponseDto> updateUserBankbook(@PathVariable Long id, @RequestBody UserBankbookDto userBankbookDto){

        return ResponseEntity.ok(userDataService.updateUserBankbook(id, userBankbookDto));
    }

    @DeleteMapping("/bankbook/{id}") //청약통장 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteUserBankbook(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteUserBankbook(id));
    }

    @GetMapping("/house/member/{houseId}") //세대구성원 조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<HouseMemberResponseDto>> readHouseMembers(@PathVariable Long houseId){

        return ResponseEntity.ok(userDataService.readHouseMembers(houseId));
    }


    @PostMapping("/house/member") //세대구성원 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> houseMember(@RequestBody HouseMemberDto houseMemberDto){

        return ResponseEntity.ok(userDataService.houseMember(houseMemberDto));
    }

    @PutMapping("/house/member/{id}") //세대구성원 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberResponseDto> updateHouseMember(@PathVariable Long id, @RequestBody HouseMemberUpdateDto houseMemberUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMember(id, houseMemberUpdateDto));
    }

    @DeleteMapping("/house/member/{id}") //세대구성원 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMember(@PathVariable Long id){

        return new ResponseEntity(userDataService.deleteHouseMember(id));
    }

    @PatchMapping("/house/member/homeless-start-date/{houseMemberId}") //세대구성원 무주택시작일 지정
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberHomelessStartDateDto> houseMemberHomelessStartDate(@PathVariable Long houseMemberId, @RequestBody HouseMemberHomelessStartDateDto houseMemberHomelessStartDateDto){
        return ResponseEntity.ok(userDataService.houseMemberHomelessStartDate(houseMemberId, houseMemberHomelessStartDateDto));
    }


    @PatchMapping("/house/holder/{id}") //세대주 지정
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseHolderDto> houseHolder(@PathVariable Long id, @RequestBody HouseHolderDto houseHolderDto){

        return ResponseEntity.ok(userDataService.houseHolder(id, houseHolderDto));
    }

    @PostMapping("/house/member/additional-info") //세대구성원 추가정보 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberAdditionalInfoResponseDto> houseMemberAdditionalInfo(@RequestBody HouseMemberAdditionalInfoDto houseMemberAdditionalInfoDto){

        return ResponseEntity.ok(userDataService.houseMemberAdditionalInfo(houseMemberAdditionalInfoDto));
    }

    @PutMapping("/house/member/additional-info/{houseMemberAdditionalInfoId}") //세대구성원 추가정보 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberAdditionalInfoResponseDto> updateHouseMemberAdditionalInfo(@PathVariable Long houseMemberAdditionalInfoId, @RequestBody HouseMemberAdditionalInfoUpdateDto houseMemberAdditionalInfoUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberAdditionalInfo(houseMemberAdditionalInfoId, houseMemberAdditionalInfoUpdateDto));
    }

    @DeleteMapping("/house/member/additional-info/{houseMemberAdditionalInfoId}") //세대구성원 추가정보 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberAdditionalInfo(@PathVariable Long houseMemberAdditionalInfoId){

        return ResponseEntity.ok(userDataService.deleteHouseMemberAdditionalInfo(houseMemberAdditionalInfoId));
    }

    @GetMapping("/house/member/property/{houseMemberId}") //세대구성원 추가정보 조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<HouseMemberPropertyResponseDto>> readHouseMemberProperties(@PathVariable Long houseMemberId){

        return ResponseEntity.ok(userDataService.readHouseMemberProperties(houseMemberId));
    }


    @PostMapping("/house/member/property") //세대구성원 자산 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> houseMemberProperty(@RequestBody HouseMemberPropertyDto houseMemberPropertyDto){

        return ResponseEntity.ok(userDataService.houseMemberProperty(houseMemberPropertyDto));
    }

    @PutMapping("/house/member/property/{houseMemberPropertyId}") //세대구성원 자산 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberPropertyResponseDto> updateHouseMemberProperty(@PathVariable Long houseMemberPropertyId, @RequestBody HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberProperty(houseMemberPropertyId, houseMemberPropertyUpdateDto));
    }

    @DeleteMapping("/house/member/property/{houseMemberPropertyId}") //세대구성원 자산 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberProperty(@PathVariable Long houseMemberPropertyId){

        return new ResponseEntity(userDataService.deleteHouseMemberProperty(houseMemberPropertyId));
    }

    @GetMapping("/house/member/chungyak/{houseMemberId}") //세대구성원 청약신청이력 조회
    public ResponseEntity<List<HouseMemberChungyakReadDto>> readHouseMemberChungyaks(@PathVariable Long houseMemberId){

        return ResponseEntity.ok(userDataService.readHouseMemberChungyaks(houseMemberId));
    }

    @PostMapping("/house/member/chungyak") //세대구성원 청약신청이력 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> houseMemberChungyak(@RequestBody HouseMemberChungyakDto houseMemberChungyakDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyak(houseMemberChungyakDto));
    }

    @PutMapping("/house/member/chungyak/{houseMemberChungyakId}") //세대구성원 청약신청이력 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakResponseDto> updateHouseMemberChungyak(@PathVariable Long houseMemberChungyakId, @RequestBody HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyak(houseMemberChungyakId, houseMemberChungyakUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/{houseMemberChungyakId}") //세대구성원 청약신청이력 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyak(@PathVariable Long houseMemberChungyakId){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyak(houseMemberChungyakId));
    }

    @PostMapping("/house/member/chungyak/restriction") //세대구성원 청약제한사랑 저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> houseMemberChungyakRestriction(@RequestBody HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){

        return ResponseEntity.ok(userDataService.houseMemberChungyakRestriction(houseMemberChungyakRestrictionDto));
    }

    @PutMapping("/house/member/chungyak/restriction/{houseMemberChungyakRestrictionId}") //세대구성원 청약제한사랑 업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<HouseMemberChungyakRestrictionResponseDto> updateHouseMemberChungyakRestriction(@PathVariable Long houseMemberChungyakRestrictionId, @RequestBody HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto){

        return ResponseEntity.ok(userDataService.updateHouseMemberChungyakRestriction(houseMemberChungyakRestrictionId, houseMemberChungyakRestrictionUpdateDto));
    }

    @DeleteMapping("/house/member/chungyak/restriction/{houseMemberChungyakRestrictionId}") //세대구성원 청약제한사랑 삭제
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity deleteHouseMemberChungyakRestriction(@PathVariable Long houseMemberChungyakRestrictionId){

        return new ResponseEntity(userDataService.deleteHouseMemberChungyakRestriction(houseMemberChungyakRestrictionId));
    }

}
