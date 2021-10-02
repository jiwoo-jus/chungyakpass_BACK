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

    HouseMemberChungyakResponseDto houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakResponseDto updateHouseMemberChungyak(Long id, HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto);

    HttpStatus deleteHouseMemberChungyak(Long id);

    HouseMemberChungyakRestrictionResponseDto houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseMemberChungyakRestrictionResponseDto updateHouseMemberChungyakRestriction(Long id, HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto);

    HttpStatus deleteHouseMemberChungyakRestriction(Long id);

}
