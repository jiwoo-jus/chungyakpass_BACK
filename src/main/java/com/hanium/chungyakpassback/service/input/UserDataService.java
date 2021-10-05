package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserDataService {
    UserBankbookResponseDto userBankbook(UserBankbookDto userBankbookDto);

    UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto);

    HttpStatus deleteUserBankbook(Long id);

    HouseResponseDto house(HouseDto houseDto);

    HouseResponseDto updateHouse(Long id, HouseUpdateDto houseUpdateDto);

    HttpStatus deleteHouse(Long id);

    HouseMemberResponseDto houseMember(HouseMemberDto houseMemberDto);

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberUpdateDto houseMemberUpdateDto);

    HttpStatus deleteHouseMember(Long id);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);

    List<HouseMemberPropertyResponseDto> houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    List<HouseMemberPropertyResponseDto> updateHouseMemberProperty(HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto);

    HttpStatus deleteHouseMemberProperty(HouseMemberPropertyDeleteDto houseMemberPropertyDeleteDto);

    List<HouseMemberChungyakResponseDto> houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    List<HouseMemberChungyakResponseDto> updateHouseMemberChungyak(HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto);

    HttpStatus deleteHouseMemberChungyak(HouseMemberChungyakDeleteDto houseMemberChungyakDeleteDto);

    List<HouseMemberChungyakRestrictionResponseDto> houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    List<HouseMemberChungyakRestrictionResponseDto> updateHouseMemberChungyakRestriction(HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto);

    HttpStatus deleteHouseMemberChungyakRestriction(HouseMemberChungyakRestrictionDeleteDto houseMemberChungyakRestrictionDeleteDto);

}
