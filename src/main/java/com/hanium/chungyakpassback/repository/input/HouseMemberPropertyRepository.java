package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseMemberPropertyRepository extends JpaRepository<HouseMemberProperty, Long> {

    List<HouseMemberProperty> findAllByHouseMember(HouseMember houseMember);
}
