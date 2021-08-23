package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.HouseMemberDto;
import com.hanium.chungyakpassback.dto.UserBankbookDto;
import com.hanium.chungyakpassback.dto.HouseDto;
import com.hanium.chungyakpassback.entity.enumtype.Relation;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseMemberRelationRepository houseMemberRelationRepository;
    private final UserBankbookRepository userBankbookRepository;

    public UserDataService(HouseRepository houseRepository, UserRepository userRepository, HouseMemberRepository houseMemberRepository, HouseMemberRelationRepository houseMemberRelationRepository, UserBankbookRepository userBankbookRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.houseMemberRepository = houseMemberRepository;
        this.houseMemberRelationRepository = houseMemberRelationRepository;
        this.userBankbookRepository = userBankbookRepository;
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


    public House house(HouseDto houseDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        House house = House.builder()
                .addressLevel1(houseDto.getAddressLevel1())
                .addressLevel2(houseDto.getAddressLevel2())
                .addressDetail(houseDto.getAddressDetail())
                .zipcode(houseDto.getZipcode())
                .build();
        houseRepository.save(house);

        HouseMember houseMember = HouseMember.builder()
                .house(house)
                .build();
        houseMemberRepository.save(houseMember);

        Relation relation = (houseDto.getSpouseHouseYn().equals(Yn.y)) ? Relation.배우자 : Relation.본인;
        houseMemberRelation(houseMember, relation);

        return house;
    }


    public HouseMember houseMember(HouseMemberDto houseMemberDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        House house = (houseMemberDto.getSpouseHouseYn().equals(Yn.y)) ? user.getSpouseHouseMember().getHouse() : user.getHouseMember().getHouse();

        HouseMember houseMember = HouseMember.builder()
                .house(house)
                .name(houseMemberDto.getName())
                .birthDate(houseMemberDto.getBirthDate())
                .foreignerYn(houseMemberDto.getForeignerYn())
                .soldierYn(houseMemberDto.getSoldierYn())
                .marriageDate(houseMemberDto.getMarriageDate())
                .homelessStartDate(houseMemberDto.getHomelessStartDate())
                .transferDate(houseMemberDto.getTransferDate())
                .build();
        houseMemberRepository.save(houseMember);

        houseMemberRelation(houseMember, houseMemberDto.getRelation());

        return houseMember;
    }


    public HouseMemberRelation houseMemberRelation(HouseMember houseMember, Relation relation){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder()
                .user(user)
                .opponent(houseMember)
                .relation(relation)
                .build();
        houseMemberRelationRepository.save(houseMemberRelation);

        if ((relation.equals(Relation.본인)) || (relation.equals(Relation.배우자))){
            if (relation.equals(Relation.본인))
                user.setHouseMember(houseMember);
            else
                user.setSpouseHouseMember(houseMember);
            userRepository.save(user);
        }

        return  houseMemberRelation;
    }
}
