package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.*;
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
    private final HouseMemberIncomeRepository houseMemberIncomeRepository;
    private final HouseMemberPropertyRepository houseMemberPropertyRepository;
    private final HouseMemberChungyakRepository houseMemberChungyakRepository;
    private final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;

    public UserDataService(HouseRepository houseRepository, UserRepository userRepository, HouseMemberRepository houseMemberRepository, HouseMemberRelationRepository houseMemberRelationRepository, UserBankbookRepository userBankbookRepository, HouseMemberIncomeRepository houseMemberIncomeRepository, HouseMemberPropertyRepository houseMemberPropertyRepository, HouseMemberChungyakRepository houseMemberChungyakRepository, HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.houseMemberRepository = houseMemberRepository;
        this.houseMemberRelationRepository = houseMemberRelationRepository;
        this.userBankbookRepository = userBankbookRepository;
        this.houseMemberIncomeRepository = houseMemberIncomeRepository;
        this.houseMemberPropertyRepository = houseMemberPropertyRepository;
        this.houseMemberChungyakRepository = houseMemberChungyakRepository;
        this.houseMemberChungyakRestrictionRepository = houseMemberChungyakRestrictionRepository;
    }


    public UserBankbook userBankbook(UserBankbookDto userBankbookDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        UserBankbook userBankbook = UserBankbook.builder()
                .user(user)
                .bank(userBankbookDto.getBank())
                .bankbook(userBankbookDto.getBankbook())
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
        houseMemberRelation(user, houseMember, relation);

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

        houseMemberRelation(user, houseMember, houseMemberDto.getRelation());

        return houseMember;
    }

    public HouseMemberRelation houseMemberRelation(User user, HouseMember houseMember, Relation relation){
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

    public HouseMemberIncome houseMemberIncome(HouseMemberIncomeDto houseMemberIncomeDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();

        HouseMemberIncome houseMemberIncome = HouseMemberIncome.builder()
                .houseMember(houseMember)
                .income(houseMemberIncomeDto.getIncome())
                .build();
        houseMemberIncomeRepository.save(houseMemberIncome);

        return houseMemberIncome;
    }

    public HouseMemberProperty houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();

        HouseMemberProperty houseMemberProperty = HouseMemberProperty.builder()
                .houseMember(houseMember)
                .property(houseMemberPropertyDto.getProperty())
                .saleRightYn(houseMemberPropertyDto.getSaleRightYn())
                .residentialBuildingYn(houseMemberPropertyDto.getResidentialBuildingYn())
                .residentialBuilding(houseMemberPropertyDto.getResidentialBuilding())
                .nonResidentialBuilding(houseMemberPropertyDto.getNonResidentialBuilding())
                .acquisitionDate(houseMemberPropertyDto.getAcquisitionDate())
                .dispositionDate(houseMemberPropertyDto.getDispositionDate())
                .exclusiveArea(houseMemberPropertyDto.getExclusiveArea())
                .amount(houseMemberPropertyDto.getAmount())
                .taxBaseDate(houseMemberPropertyDto.getTaxBaseDate())
                .build();
        houseMemberPropertyRepository.save(houseMemberProperty);

        return houseMemberProperty;
    }

    public HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();

        HouseMemberChungyak houseMemberChungyak = HouseMemberChungyak.builder()
                .houseMember(houseMember)
                .houseName(houseMemberChungyakDto.getHouseName())
                .supply(houseMemberChungyakDto.getSupply())
                .specialSupply(houseMemberChungyakDto.getSpecialSupply())
                .housingType(houseMemberChungyakDto.getHousingType())
                .ranking(houseMemberChungyakDto.getRanking())
                .result(houseMemberChungyakDto.getResult())
                .preliminaryNumber(houseMemberChungyakDto.getPreliminaryNumber())
                .winningDate(houseMemberChungyakDto.getWinningDate())
                .raffle(houseMemberChungyakDto.getRaffle())
                .cancelWinYn(houseMemberChungyakDto.getCancelWinYn())
                .build();
        houseMemberChungyakRepository.save(houseMemberChungyak);

        return houseMemberChungyak;
    }

    public HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();

        HouseMemberChungyakRestriction houseMemberChungyakRestriction = HouseMemberChungyakRestriction.builder()
                .houseMember(houseMember)
                .houseMemberChungyak(houseMemberChungyakRepository.findById(houseMemberChungyakRestrictionDto.getHouseMemberChungyakId()).get())
                .reWinningRestrictedDate(houseMemberChungyakRestrictionDto.getReWinningRestrictedDate())
                .specialSupplyRestrictedYn(houseMemberChungyakRestrictionDto.getSpecialSupplyRestrictedYn())
                .unqualifiedSubscriberRestrictedDate(houseMemberChungyakRestrictionDto.getUnqualifiedSubscriberRestrictedDate())
                .regulatedAreaFirstPriorityRestrictedDate(houseMemberChungyakRestrictionDto.getRegulatedAreaFirstPriorityRestrictedDate())
                .additionalPointSystemRestrictedDate(houseMemberChungyakRestrictionDto.getAdditionalPointSystemRestrictedDate())
                .build();
        houseMemberChungyakRestrictionRepository.save(houseMemberChungyakRestriction);

        return houseMemberChungyakRestriction;
    }
}
