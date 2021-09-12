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

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberUpdateDto houseMemberUpdateDto);

    HouseMemberPropertyResponseDto houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberPropertyResponseDto updateHouseMemberProperty(Long id, HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto);

    HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);


}
