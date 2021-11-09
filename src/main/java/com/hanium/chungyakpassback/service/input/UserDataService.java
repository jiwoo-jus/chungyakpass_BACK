package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserDataService {

    UserBankbookResponseDto readUserBankbook();

    UserBankbookResponseDto userBankbook(UserBankbookDto userBankbookDto);

    UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto);

    HttpStatus deleteUserBankbook(Long id);

    HouseReadDto readHouses();

    HouseResponseDto house(HouseDto houseDto);

    HouseResponseDto updateHouse(Long id, HouseUpdateDto houseUpdateDto);

    HttpStatus deleteHouse(Long id);

    List<HouseMemberResponseDto> readHouseMembers(Long houseMemberId);

    HouseMemberResponseDto houseMember(HouseMemberDto houseMemberDto);

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberUpdateDto houseMemberUpdateDto);

    HttpStatus deleteHouseMember(Long id);

    HouseMemberHomelessStartDateDto houseMemberHomelessStartDate(Long houseMemberId, HouseMemberHomelessStartDateDto houseMemberHomelessStartDateDto);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);

    List<HouseMemberPropertyResponseDto> readHouseMemberProperties(Long houseMemberId);

    HouseMemberPropertyResponseDto houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberPropertyResponseDto updateHouseMemberProperty(Long houseMemberPropertyId, HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto);

    HttpStatus deleteHouseMemberProperty(Long houseMemberPropertyId);

    List<HouseMemberChungyakReadDto> readHouseMemberChungyaks(Long houseMemberId);

    HouseMemberChungyakResponseDto houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakResponseDto updateHouseMemberChungyak(Long houseMemberChungyakId, HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto);

    HttpStatus deleteHouseMemberChungyak(Long houseMemberChungyakId);

    HouseMemberChungyakRestrictionResponseDto houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseMemberChungyakRestrictionResponseDto updateHouseMemberChungyakRestriction(Long houseMemberChungyakRestrictionId, HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto);

    HttpStatus deleteHouseMemberChungyakRestriction(Long houseMemberChungyakRestrictionId);

}