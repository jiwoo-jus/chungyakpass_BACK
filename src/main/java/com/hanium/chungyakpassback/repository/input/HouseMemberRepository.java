package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface HouseMemberRepository extends JpaRepository<HouseMember, Long> {

    void deleteAllByHouse(House house);

}
