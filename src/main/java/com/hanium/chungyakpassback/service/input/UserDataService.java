package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;
import org.springframework.http.HttpStatus;

public interface UserDataService {
    UserBankbookResponseDto userBankbook(User user, UserBankbookDto userBankbookDto);

    UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto);

    HttpStatus deleteUserBankbook(Long id);

    HouseResponseDto house(User user, HouseDto houseDto);

    HouseResponseDto updateHouse(Long id, User user, HouseUpdateDto houseUpdateDto);

    HttpStatus deleteHouse(Long id);

    HouseMemberResponseDto houseMember(User user, HouseMemberDto houseMemberDto);

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberUpdateDto houseMemberUpdateDto);

//    HouseMemberResponseDto deleteHouseMember(Long id);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);

    HouseMemberPropertyResponseDto houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberPropertyResponseDto updateHouseMemberProperty(Long id, HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto);

//    HouseMemberPropertyResponseDto deleteHouseMemberProperty(Long id);

    HouseMemberChungyakResponseDto houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakResponseDto updateHouseMemberChungyak(Long id, HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto);

//    HouseMemberChungyakResponseDto deleteHouseMemberChungyak(Long id);

    HouseMemberChungyakRestrictionResponseDto houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseMemberChungyakRestrictionResponseDto updateHouseMemberChungyakRestriction(Long id, HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto);

//    HouseMemberChungyakRestrictionResponseDto deleteHouseMemberChungyakRestriction(Long id);

}
