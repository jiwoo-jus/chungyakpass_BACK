package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.enumtype.Relation;

public interface UserDataService {
    UserBankbook userBankbook(User user, UserBankbookDto userBankbookDto);

    House house(User user, HouseDto houseDto);

    HouseMember houseMember(User user, HouseMemberDto houseMemberDto);

    HouseMemberRelation houseMemberRelation(User user, HouseMember houseMember, Relation relation);

    HouseMemberProperty houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto);

    HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto);

    HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto);

    HouseHolderDto houseHolder(User user, HouseHolderDto houseHolderDto);


}
