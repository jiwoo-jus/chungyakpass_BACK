package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.HouseMemberUserDto;
import com.hanium.chungyakpassback.dto.UserBankbookDto;
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
    private final UserBankbookRepository userBankbookRepository;

    public UserDataService(HouseholdRepository householdRepository, AddressRepository addressRepository, UserRepository userRepository, HouseholdMemberRepository householdMemberRepository, HouseholdMemberRelationRepository householdMemberRelationRepository, UserBankbookRepository userBankbookRepository) {
        this.householdRepository = householdRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.householdMemberRepository = householdMemberRepository;
        this.householdMemberRelationRepository = householdMemberRelationRepository;
        this.userBankbookRepository = userBankbookRepository;
    }

    public HouseMember houseMemberUser(HouseMemberUserDto houseMemberUserDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        Address address = Address.builder()
                .addressLevel1(houseMemberUserDto.getAddressLevel1())
                .addressLevel2(houseMemberUserDto.getAddressLevel2())
                .addressDetail(houseMemberUserDto.getAddressDetail())
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
        user.setHouseMember(houseMember);
        userRepository.save(user);
        return houseMember;
    }

    public UserBankbook userBankbook(UserBankbookDto userBankbookDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        UserBankbook userBankbook = UserBankbook.builder()
                .user(user)
                .bank(userBankbookDto.getBank())
                .bankbookType(userBankbookDto.getBankbookType())
                .joinDate(userBankbookDto.getJoinDate())
                .deposit(userBankbookDto.getDeposit())
                .paymentsCount(userBankbookDto.getPaymentsCount())
                .validYn(userBankbookDto.getValidYn())
                .build();

        userBankbookRepository.save(userBankbook);
        return userBankbook;
    }
}
