package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseMemberAdditionalInfoRepository extends JpaRepository<HouseMemberAdditionalInfo, Long> {
    HouseMemberAdditionalInfo findByHouseMember(HouseMember houseMember);
    void deleteByHouseMember(HouseMember houseMember);
}
