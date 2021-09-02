package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;

public interface UserDataService {
    UserBankbookResponseDto userBankbook(User user, UserBankbookDto userBankbookDto);

    UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto);

    House house(User user, HouseDto houseDto);

    House updateHouse(Long id, User user, HouseDto houseDto);

//    HouseDto patchHouse(User user, HouseDto houseDto);

    HouseMemberResponseDto houseMember(User user, HouseMemberDto houseMemberDto);

    HouseMemberResponseDto updateHouseMember(Long id, HouseMemberDto houseMemberDto);

//    HouseMemberRelation houseMemberRelation(User user, HouseMember houseMember, Relation relation);

    HouseMemberProperty houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto);


}
