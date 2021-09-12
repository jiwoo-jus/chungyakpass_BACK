package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;

public interface UserDataService {
    UserBankbookResponseDto userBankbook(User user, UserBankbookDto userBankbookDto);

    UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto);

    HouseResponseDto house(User user, HouseDto houseDto);

    HouseResponseDto updateHouse(Long id, User user, HouseUpdateDto houseUpdateDto);

//    HouseDto patchHouse(User user, HouseDto houseDto);

    HouseMemberResponseDto houseMember(User user, HouseMemberDto houseMemberDto);

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberDto houseMemberDto);

    HouseMemberPropertyResponseDto houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

//    HouseMemberPropertyDto updateHouseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);


}
