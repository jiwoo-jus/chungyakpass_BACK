package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.entity.standard.AddressLevel2;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel2Repository;
import com.hanium.chungyakpassback.repository.standard.RelationRepository;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserDataServiceImpl implements UserDataService{
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseMemberRelationRepository houseMemberRelationRepository;
    private final UserBankbookRepository userBankbookRepository;
    private final HouseMemberPropertyRepository houseMemberPropertyRepository;
    private final HouseMemberChungyakRepository houseMemberChungyakRepository;
    private final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;

    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Repository addressLevel2Repository;
    private final RelationRepository relationRepository;


    @Transactional(rollbackFor = Exception.class)
    public UserBankbookResponseDto userBankbook(User user, UserBankbookDto userBankbookDto){
        if (userBankbookRepository.findByUser(user).orElse(null) != null)
            throw new CustomException(ErrorCode.DUPLICATE_BANKBOOK);

        return new UserBankbookResponseDto(userBankbookRepository.save(userBankbookDto.toEntity(user)));
    }

    @Transactional(rollbackFor = Exception.class)
    public UserBankbookResponseDto updateUserBankbook(Long id, UserBankbookDto userBankbookDto) {
        UserBankbook userBankbook = userBankbookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BANKBOOK));

        return new UserBankbookResponseDto(userBankbookRepository.save(userBankbook.updateUserBankbook(userBankbookDto)));
    }


    @Transactional(rollbackFor = Exception.class)
    public HouseResponseDto house(User user, HouseDto houseDto){
        if ((houseDto.getSpouseHouseYn().equals(Yn.y) && user.getSpouseHouse() != null) || (houseDto.getSpouseHouseYn().equals(Yn.n) && user.getHouse() != null))
            throw new CustomException(ErrorCode.DUPLICATE_HOUSE);

        AddressLevel1 addressLevel1 = addressLevel1Repository.findByAddressLevel1(houseDto.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        AddressLevel2 addressLevel2 = addressLevel2Repository.findByAddressLevel1AndAddressLevel2(addressLevel1, houseDto.getAddressLevel2()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL2));
        House house = houseDto.toEntity(addressLevel1, addressLevel2);
        houseRepository.save(house);

        if (houseDto.getSpouseHouseYn().equals(Yn.y))
            user.setSpouseHouse(house);
        else user.setHouse(house);
        userRepository.save(user);

        return new HouseResponseDto(house);
    }

    @Transactional(rollbackFor = Exception.class)
    public HouseResponseDto updateHouse(Long id, User user, HouseUpdateDto houseUpdateDto){
        House house = houseRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));

        AddressLevel1 addressLevel1 = addressLevel1Repository.findByAddressLevel1(houseUpdateDto.getAddressLevel1()).get();
        AddressLevel2 addressLevel2 = addressLevel2Repository.findByAddressLevel1AndAddressLevel2(addressLevel1, houseUpdateDto.getAddressLevel2()).get();
        house = house.update(addressLevel1, addressLevel2, houseUpdateDto);
        return new HouseResponseDto(houseRepository.save(house));
    }



    @Transactional(rollbackFor = Exception.class)
    public HouseMemberResponseDto houseMember(User user, HouseMemberDto houseMemberDto){
        Optional<House> optionalHouse = Optional.ofNullable((houseMemberDto.getSpouseHouseYn().equals(Yn.y)) ? user.getSpouseHouse() : user.getHouse());
        House house = optionalHouse.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));

        com.hanium.chungyakpassback.entity.standard.Relation relation = relationRepository.findByRelation(houseMemberDto.getRelation()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL2));
        if (relation.getOnlyOneYn().equals(Yn.y) && houseMemberRelationRepository.findByUserAndRelation(user, relation).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_RELATION);

        HouseMember houseMember = houseMemberDto.toEntity(house);
        houseMemberRepository.save(houseMember);

        HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder()
                .user(user).opponent(houseMember).relation(relation)
                .build();
        houseMemberRelationRepository.save(houseMemberRelation);

        if ((relation.getRelation().equals(Relation.본인)) || (relation.getRelation().equals(Relation.배우자))){
            if (relation.getRelation().equals(Relation.본인))
                user.setHouseMember(houseMember);
            else user.setSpouseHouseMember(houseMember);
            userRepository.save(user);
        }

        return new HouseMemberResponseDto(houseMember, houseMemberRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    public HouseMemberResponseDto updateHouseMember(Long id, HouseMemberDto houseMemberDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = houseMemberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HOUSE_MEMBER));

        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
        com.hanium.chungyakpassback.entity.standard.Relation presentRelation = houseMemberRelation.getRelation();
        com.hanium.chungyakpassback.entity.standard.Relation changedRelation = relationRepository.findByRelation(houseMemberDto.getRelation()).get();
        if (!presentRelation.equals(changedRelation)) {
            if (changedRelation.getOnlyOneYn().equals(Yn.y) && houseMemberRelationRepository.findByUserAndRelation(user, changedRelation).isPresent())
                throw new CustomException(ErrorCode.DUPLICATE_RELATION);

            houseMemberRelation.setRelation(changedRelation);
            houseMemberRelationRepository.save(houseMemberRelation);

            if ((presentRelation.getRelation().equals(Relation.본인)) || (presentRelation.getRelation().equals(Relation.배우자))){
                if (presentRelation.getRelation().equals(Relation.본인))
                    user.setHouseMember(null);
                else user.setSpouseHouseMember(null);
                userRepository.save(user);
            }
            if ((changedRelation.getRelation().equals(Relation.본인)) || (changedRelation.getRelation().equals(Relation.배우자))) {
                if (changedRelation.getRelation().equals(Relation.본인))
                    user.setHouseMember(houseMember);
                else user.setSpouseHouseMember(houseMember);
                userRepository.save(user);
            }
        }

        houseMember.updateHouseMember(houseMemberDto);
        houseMemberRepository.save(houseMember);

        return new HouseMemberResponseDto(houseMember, houseMemberRelation);
    }


    public HouseMemberPropertyResponseDto houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto){
        HouseMember houseMember = houseMemberRepository.findById(houseMemberPropertyDto.getHouseMemberId()).get();

        HouseMemberProperty houseMemberProperty = houseMemberPropertyDto.toEntity(houseMember);
        houseMemberPropertyRepository.save(houseMemberProperty);

        return new HouseMemberPropertyResponseDto(houseMemberProperty);
    }

    public HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto){
        HouseMember houseMember = houseMemberRepository.findById(houseMemberChungyakDto.getHouseMemberId()).get();

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
        HouseMemberChungyak houseMemberChungyak = houseMemberChungyakRepository.findById(houseMemberChungyakRestrictionDto.getHouseMemberChungyakId()).get();

        HouseMemberChungyakRestriction houseMemberChungyakRestriction = HouseMemberChungyakRestriction.builder()
                .houseMemberChungyak(houseMemberChungyak)
                .reWinningRestrictedDate(houseMemberChungyakRestrictionDto.getReWinningRestrictedDate())
                .specialSupplyRestrictedYn(houseMemberChungyakRestrictionDto.getSpecialSupplyRestrictedYn())
                .unqualifiedSubscriberRestrictedDate(houseMemberChungyakRestrictionDto.getUnqualifiedSubscriberRestrictedDate())
                .regulatedAreaFirstPriorityRestrictedDate(houseMemberChungyakRestrictionDto.getRegulatedAreaFirstPriorityRestrictedDate())
                .additionalPointSystemRestrictedDate(houseMemberChungyakRestrictionDto.getAdditionalPointSystemRestrictedDate())
                .build();
        houseMemberChungyakRestrictionRepository.save(houseMemberChungyakRestriction);

        return houseMemberChungyakRestriction;
    }

    public HouseHolderDto houseHolder(Long id, HouseHolderDto houseHolderDto){
        House house = houseRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));
        HouseMember houseMember = houseMemberRepository.findById(houseHolderDto.getHouseMemberId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HOUSE_MEMBER));

        house.setHouseHolder(houseMember);
        houseRepository.save(house);

        return houseHolderDto;
    }
}



//    @Transactional(rollbackFor = Exception.class)
//    public HouseDto patchHouse(User user, HouseDto houseDto){
//        Optional<House> optionalHouse = Optional.ofNullable(houseDto.getSpouseHouseYn().equals(Yn.y) ? user.getSpouseHouse() : user.getHouse());
//        if (optionalHouse.isPresent()){
//            House house = optionalHouse.get();
//            if (StringUtils.isNotBlank(houseDto.getAddressLevel1().toString()))
//                house.setAddressLevel1(addressLevel1Repository.findByAddressLevel1(houseDto.getAddressLevel1()));
//            if (StringUtils.isNotBlank(houseDto.getAddressLevel2().toString()))
//                house.setAddressLevel2(addressLevel2Repository.findByAddressLevel2(houseDto.getAddressLevel2()));
//            if (StringUtils.isNotBlank(houseDto.getAddressDetail()))
//                house.setAddressDetail(houseDto.getAddressDetail());
//            if (StringUtils.isNotBlank(houseDto.getZipcode()))
//                house.setZipcode(houseDto.getZipcode());
//            houseRepository.save(house);
//        }
//        return houseDto;
//    }


//    public HouseMemberRelation houseMemberRelation(User user, HouseMember houseMember, Relation relation){
//        HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder()
//                .user(user)
//                .opponent(houseMember)
//                .relation(relation)
//                .build();
//        houseMemberRelationRepository.save(houseMemberRelation);
//
//        if ((relation.equals(Relation.본인)) || (relation.equals(Relation.배우자))){
//            if (relation.equals(Relation.본인))
//                user.setHouseMember(houseMember);
//            else
//                user.setSpouseHouseMember(houseMember);
//            userRepository.save(user);
//        }
//
//        return  houseMemberRelation;
//    }