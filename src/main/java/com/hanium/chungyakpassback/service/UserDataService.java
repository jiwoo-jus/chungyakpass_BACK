package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.HouseholdMemberUserDto;
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

    public Household saveAddress(HouseholdMemberUserDto householdMemberUserDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        Address address = Address.builder()
                .address_level1(householdMemberUserDto.getAddress_level1())
                .address_level2(householdMemberUserDto.getAddress_level2())
                .address_detail(householdMemberUserDto.getAddress_detail())
                .zipcode(householdMemberUserDto.getZipcode())
                .build();
        Household household = Household.builder()
                .address(address)
                .build();
        HouseholdMember householdMember = HouseholdMember.builder()
                .household(household)
                .householderYn(householdMemberUserDto.getHouseholderYn())
                .name(householdMemberUserDto.getName())
                .birthDate(householdMemberUserDto.getBirthDate())
                .foreignerYn(householdMemberUserDto.getForeignerYn())
                .soldierYn(householdMemberUserDto.getSoldierYn())
                .marriageDate(householdMemberUserDto.getMarriageDate())
                .homelessStartDate(householdMemberUserDto.getHomelessStartDate())
                .transferDate(householdMemberUserDto.getTransferDate())
                .build();
        HouseholdMemberRelation householdMemberRelation = HouseholdMemberRelation.builder()
                .user(user)
                .opponent(householdMember)
                .relation(Relation.본인)
                .build();

        addressRepository.save(address);
        householdRepository.save(household);
        householdMemberRepository.save(householdMember);
        householdMemberRelationRepository.save(householdMemberRelation);
        return household;
    }
}
