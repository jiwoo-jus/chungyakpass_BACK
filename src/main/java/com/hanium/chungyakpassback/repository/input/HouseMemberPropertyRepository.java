package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseMemberPropertyRepository extends JpaRepository<HouseMemberProperty, Long> {

    void deleteAllByHouseMember(HouseMember houseMember);
    List<HouseMemberProperty> findAllByHouseMember(HouseMember houseMember);
}
