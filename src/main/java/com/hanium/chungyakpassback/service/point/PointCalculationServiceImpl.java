package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.GeneralMinyeongPointDto;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.standard.Bankbook;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.ResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.BankbookRepository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointCalculationServiceImpl implements PointCalculationService {
    final HouseMemberRepository houseMemberRepository;
    final BankbookRepository bankbookRepository;
    final HouseRepository houseRepository;
    final UserBankbookRepository userBankbookRepository;
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    List<Boolean> bothParentsIsHomelessYnList = new ArrayList<>();//부모님 두분다 무주택자인지 확인하는 리스트
    List<LocalDate> lateDateList = new ArrayList<>();//배우자와 본인중 무주택시점이 늦은날을 저장하는 리스트
    List<Boolean> resultOfHomelessYnList = new ArrayList<>();//무주택여부를 저장하는 리스트
    LocalDate lateDate;

    public List periodHomeless(HouseMember houseMember) {
        if ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) < 30 && houseMember.getMarriageDate() == null) || houseMember.getForeignerYn().equals(Yn.y)) {//만30세미만 미혼이거나 무주택시작일이 없으면 0점
            return lateDateList;
        }
        else {
            if ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 30) || houseMember.getMarriageDate() != null) {//만나이가 30세 이상이거나 결혼했으먼
                LocalDate birthDayAfter30Year = houseMember.getBirthDay().plusYears(30);
                if (houseMember.getMarriageDate() == null || ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 30) && houseMember.getMarriageDate() != null)) {//(1)만30세이상 미혼이거나 만30세 이후 혼인신고 시 만 30세 생일과 무주택이 된 날짜중에 늦은 날
                    if (birthDayAfter30Year.isAfter(houseMember.getHomelessStartDate())) {
                        lateDate = birthDayAfter30Year;
                    } else {
                        lateDate = houseMember.getHomelessStartDate();
                    }
                } else {//만30세 이전 혼인신고시 혼인신고일과 무주택된 날중 늦은 날짜
                    if (houseMember.getMarriageDate().isAfter(houseMember.getHomelessStartDate())) {
                        lateDate = houseMember.getMarriageDate();
                    } else {
                        lateDate = houseMember.getHomelessStartDate();
                    }
                }
                lateDateList.add(lateDate);
            }
        }
        return lateDateList;
    }







   @Transactional(rollbackFor = Exception.class)
    public Integer periodOfHomelessness(User user) {
        int point = 0;
        Integer totalHouseCount = generalPrivateVerificationServiceImpl.getHouseMember(user);
        if (totalHouseCount == 0) {
                if(user.getSpouseHouseMember()!=null&&user.getSpouseHouseMember().getMarriageDate()==null){
                    throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);
                }
                else if (user.getSpouseHouseMember() == null || (user.getSpouseHouseMember().getMarriageDate().isAfter(user.getSpouseHouseMember().getHomelessStartDate()))) {

                    lateDateList = periodHomeless(user.getHouseMember());
                    System.out.println(lateDateList);
                }
                else {
                    periodHomeless(user.getHouseMember());
                    periodHomeless(user.getSpouseHouseMember());
                }

            // 배우자가 결혼 후에 팔았을 때 또는 집판날과 결혼한날이 같을 때
            //반환값을 가지고 늦은 순서대로 정렬
            lateDateList.sort(Collections.reverseOrder());
            LocalDate mostLateDate = lateDateList.get(0);
            //무주택기간을 기간으로 계산함
            int periodOfHomelessness = generalPrivateVerificationServiceImpl.calcAmericanAge(mostLateDate);
            for (int z = 1; z <= 15; z++) {
                if (periodOfHomelessness < z) {
                    return point = z * 2;
                } else point = 32;
            }
        }
        return point;
    }

    public boolean homelessYn(HouseMember houseMember) {
        System.out.println("homelessYn1");
        List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

        //무주택여부계산
        for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
            System.out.println(houseMemberProperty.getProperty());
            System.out.println("homelessYn2");
            Optional<HouseMemberRelation> houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember);
            if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                if ((houseMemberRelation.get().getRelation().getRelation().equals(Relation.부)||houseMemberRelation.get().getRelation().getRelation().equals(Relation.모)|| houseMemberRelation.get().getRelation().getRelation().equals(Relation.조부)||houseMemberRelation.get().getRelation().getRelation().equals(Relation.조모)|| houseMemberRelation.get().getRelation().getRelation().equals(Relation.배우자의부)||houseMemberRelation.get().getRelation().getRelation().equals(Relation.배우자의모)||houseMemberRelation.get().getRelation().getRelation().equals(Relation.배우자의조부)||houseMemberRelation.get().getRelation().getRelation().equals(Relation.배우자의조모))) {
                    if(generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 60) {
                            resultOfHomelessYnList.add(Boolean.TRUE);
                    }
                }
                if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) {
                    resultOfHomelessYnList.add(Boolean.TRUE);
                } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) {
                    resultOfHomelessYnList.add(Boolean.TRUE);
                }
                else{
                    resultOfHomelessYnList.add(Boolean.FALSE);
                }
            } else
            {
                resultOfHomelessYnList.add(Boolean.FALSE);
            }
        }
        System.out.println("1111111111111111111111111111");
        //System.out.println("resultOfHomelessYnList!!!" + resultOfHomelessYnList);
        //System.out.println("resultOfHomelessYnList!!!" + resultOfHomelessYnList.get(0).toString());

        //무주택리스트를 받아서 하나라도 false를 반환하면 false반환
        if (resultOfHomelessYnList.contains(Boolean.FALSE)) {
            System.out.println("false");
            return false;
        } else
            System.out.println("true");
            return true;
    }

    public Integer countOfDependents(HouseMemberRelation memberRelation, int parents) {
        //무주택여부를 확인하기 위해 meetHouseHaving메소드 실행 시 true반환하면 parents++하고 houseMemberList3에 true값 저장
        System.out.println("!!!!!!!!!numberOfFamilyfirst");
        if (homelessYn(memberRelation.getOpponent())) {
            parents++;
            bothParentsIsHomelessYnList.add(Boolean.TRUE);
        } else {//아니면 flase저장
            bothParentsIsHomelessYnList.add(Boolean.FALSE);
        }
        //houseMemberList3가 하나라도 flase반환하면 0
        int numberOfFamily;
        if (bothParentsIsHomelessYnList.contains(Boolean.FALSE)) {
            numberOfFamily = 0;
        } else {//아니면 parents반환
            numberOfFamily = parents;
        }
        System.out.println("!!!!!!!!!numberOfFamilylast");
        return numberOfFamily;
    }

    public Integer numberOfFamily(User user,HouseMemberRelation houseMemberRelation ,  GeneralMinyeongPointDto generalMinyeongPoint, int numberOfFamily, int parents) {
        System.out.println("!!!!!!!!!numberOfFamilyfirst");

            if (generalMinyeongPoint.getStayOverYn().equals(Yn.n)&&generalMinyeongPoint.getSameResidentRegistrationYn().equals(Yn.y)&&houseMemberRelation.getOpponent().getForeignerYn().equals(Yn.n)){

                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부)||houseMemberRelation.getRelation().getRelation().equals(Relation.모))  && houseMemberRelation.getUser().equals(user) ) {
                    numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation,parents);
                }
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.조부)||houseMemberRelation.getRelation().getRelation().equals(Relation.조모)) && houseMemberRelation.getUser().equals(user)) {
                    numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation,parents);
                }
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부)||houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모)) && houseMemberRelation.getUser().equals(user)) {
                    numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation,parents);
                }

                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부)||houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모)) && houseMemberRelation.getUser().equals(user)) {
                    numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation,parents);
                }
        }
        System.out.println("!!!!!!!!!numberOfFamilylast");
        return numberOfFamily;

    }

   @Transactional(rollbackFor = Exception.class)
    public Integer numberOfDependents(User user, GeneralMinyeongPointDto generalMinyeongPointDto) { //부양가족 산출하기
        int numberOfFamily = 0;
        int point = 0;
        int parents = 0;
        for(int i=0;i<generalMinyeongPointDto.getGeneralMinyeongPointDtoList().size();i++) {
            GeneralMinyeongPointDto generalMinyeongPoint = generalMinyeongPointDto.getGeneralMinyeongPointDtoList().get(i);
            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponentId(generalMinyeongPoint.getHouseMemberId()).get();

            if (user.getSpouseHouseMember() != null) {//배우자는 무조건 포함
                numberOfFamily++;
            }
            if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) { //배우자와 같은 세대이거나, 미혼일 경우
                if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) //본인이 세대주일 때 무주택직계존속 포함
                {
                    numberOfFamily = numberOfFamily(user, houseMemberRelation,generalMinyeongPoint,numberOfFamily, parents);
                }
            }
            else if (user.getHouse() != user.getSpouseHouse()) {
                if (user.getSpouseHouse().getHouseHolder().getId().equals(user.getSpouseHouseMember().getId())) { //배우자가 세대주일 때 무주택직계존속 포함
                    numberOfFamily = numberOfFamily(user, houseMemberRelation,generalMinyeongPoint,numberOfFamily,parents);
                }
            }
            else {
                if (houseMemberRelation.getOpponent().getForeignerYn().equals(Yn.n) && ((houseMemberRelation.getRelation().getRelation().equals(Relation.손자녀) && generalMinyeongPoint.getParentsDeathYn().equals(Yn.y)) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반))) {//부모가 죽은 미혼 손자녀
                        if (houseMemberRelation.getOpponent().getMarriageDate() == null) {//미혼 자녀
                            if (generalMinyeongPoint.getDivorceYn().equals(Yn.n)) {
                                if (generalPrivateVerificationServiceImpl.calcAmericanAge(houseMemberRelation.getOpponent().getBirthDay()) < 30) {
                                    if (!(generalMinyeongPoint.getNowStayOverYn().equals(Yn.y) && generalMinyeongPoint.getStayOverYn().equals(Yn.y))) {//현재 체류여부
                                        numberOfFamily++;
                                    }
                                }
                                else {
                                    if (generalMinyeongPoint.getStayOverYn().equals(Yn.n)) {// 체류여부
                                        if (generalMinyeongPoint.getSameResidentRegistrationYn().equals(Yn.y)) {
                                            numberOfFamily++;
                                        }
                                    }

                                }
                            }

                    }
                }
            }
        }

        System.out.println(numberOfFamily);
        for (int z = 1; z <= 6; z++) {
            if (numberOfFamily < z) {
                return point = z * 5;
            } else point = 35;
        }
        return point;
    }

    public int periodOfMonth(LocalDate joinDate) {
        LocalDate now = LocalDate.now();
        Period period = joinDate.until(now);
        return period.getYears() * 12 + period.getMonths();
    }

   @Transactional(rollbackFor = Exception.class)
    public Integer bankbookJoinPeriod(User user) {
        int point = 0;
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        Optional<Bankbook> stdBankbook = bankbookRepository.findByBankbook(optUserBankbook.get().getBankbook());
            if (stdBankbook.get().getPrivateHousingSupplyIsPossible().equals(Yn.y)) {
                int joinPeriodOfMonth = periodOfMonth(optUserBankbook.get().getJoinDate());
                int joinPeriodOfYear = generalPrivateVerificationServiceImpl.calcAmericanAge(optUserBankbook.get().getJoinDate());
                if (joinPeriodOfMonth < 12) {
                    for (int z = 1; z <= 2; z++) {
                        if (joinPeriodOfMonth < z * 6) {
                            point = z;
                        }
                    }
                } else {
                    for (int z = 2; z <= 15; z++) {
                        if (joinPeriodOfYear < z) {
                            return point = z + 1;
                        } else point = 17;
                    }
                }
                return point;
            }
        else{
                throw new CustomException(ErrorCode.BAD_REQUEST_BANKBOOK);
        }
    }
}







