package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.HouseMemberUserDto;
import com.hanium.chungyakpassback.entity.enumtype.Relation;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final HouseholdRepository householdRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final HouseholdMemberRepository householdMemberRepository;
    private final HouseholdMemberRelationRepository householdMemberRelationRepository;

    public UserDataService(HouseholdRepository householdRepository, AddressRepository addressRepository, UserRepository userRepository, HouseholdMemberRepository householdMemberRepository, HouseholdMemberRelationRepository householdMemberRelationRepository) {
        this.householdRepository = householdRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.householdMemberRepository = householdMemberRepository;
        this.householdMemberRelationRepository = householdMemberRelationRepository;
    }

    public HouseMember houseMemberUser(HouseMemberUserDto houseMemberUserDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        Address address = Address.builder()
                .address_level1(houseMemberUserDto.getAddress_level1())
                .address_level2(houseMemberUserDto.getAddress_level2())
                .address_detail(houseMemberUserDto.getAddress_detail())
                .zipcode(houseMemberUserDto.getZipcode())
                .build();
        House house = House.builder()
                .address(address)
                .build();
        HouseMember houseMember = HouseMember.builder()
                .house(house)
                .householderYn(houseMemberUserDto.getHouseholderYn())
                .name(houseMemberUserDto.getName())
                .birthDate(houseMemberUserDto.getBirthDate())
                .foreignerYn(houseMemberUserDto.getForeignerYn())
                .soldierYn(houseMemberUserDto.getSoldierYn())
                .marriageDate(houseMemberUserDto.getMarriageDate())
                .homelessStartDate(houseMemberUserDto.getHomelessStartDate())
                .transferDate(houseMemberUserDto.getTransferDate())
                .build();
        HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder()
                .user(user)
                .opponent(houseMember)
                .relation(Relation.본인)
                .build();

        addressRepository.save(address);
        householdRepository.save(house);
        householdMemberRepository.save(houseMember);
        householdMemberRelationRepository.save(houseMemberRelation);
        return houseMember;
    }
}
