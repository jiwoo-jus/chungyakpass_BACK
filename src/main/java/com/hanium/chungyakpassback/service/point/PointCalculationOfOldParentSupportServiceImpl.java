package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.SpecialPointOfOldParentsSupportDto;
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
public class PointCalculationOfOldParentSupportServiceImpl implements PointCalculationOfOldParentSupportService {
    final HouseMemberRepository houseMemberRepository;
    final BankbookRepository bankbookRepository;
    final HouseRepository houseRepository;
    final UserBankbookRepository userBankbookRepository;
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;

    //세대구성원별 무주택기간을 구하는 메소드
    public List periodHomeless(HouseMember houseMember, List lateDateList) {
        //30세이상이거나 결혼한경우
        if ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 30) || houseMember.getMarriageDate() != null) {//만나이가 30세 이상이거나 결혼했으먼
            LocalDate birthDayAfter30Year = houseMember.getBirthDay().plusYears(30);//세대구성원의 30세 생일
            LocalDate lateDate;//무주택기간

            //(1)만30세이상 미혼이거나 만30세 이후 혼인신고 시 만 30세 생일과 무주택이 된 날짜중에 늦은 날
            if (houseMember.getMarriageDate() == null || ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 30) && houseMember.getMarriageDate() != null)) {
                if (birthDayAfter30Year.isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = birthDayAfter30Year;
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
            //만30세 이전 혼인신고시 혼인신고일과 무주택된 날중 늦은 날짜
            else {
                if (houseMember.getMarriageDate().isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = houseMember.getMarriageDate();
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
            lateDateList.add(lateDate);
        }
        return lateDateList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfHomelessness(User user) {
        int periodOfHomelessnessGetPoint = 0;
        List<LocalDate> lateDateList = new ArrayList<>(); //무주택시작일을 담아놓은 리스트
        if (user.getSpouseHouseMember() != null && user.getSpouseHouseMember().getMarriageDate() == null) {//배우자가 있는데 혼인신고일이 없으면 에러발생
            throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);
        } else {
            //신청자가 30세미만에 미혼이거나 외국인이면 0점
            if ((generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getBirthDay()) < 30 && user.getSpouseHouseMember() == null) || user.getHouseMember().getForeignerYn().equals(Yn.y)) {
                return periodOfHomelessnessGetPoint =0;
            }
            else {
                //배우자가 없거나 배우자가 결혼전에 무주택자가 된경우
                if (user.getSpouseHouseMember() == null || (user.getSpouseHouseMember().getMarriageDate().isAfter(user.getSpouseHouseMember().getHomelessStartDate()))) {

                    //배우자가 없거나 배우자와 같이 거주중이면 신청자와 신청자의 직계존속의 무주택기간을 산출
                    if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouseMember() == null) {
                        periodHomeless(user.getHouseMember(), lateDateList);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            //신청자의 직계존속이 집을 소유한 적이 있으면 무주택산정 리스트에 추가한다.
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                    } else {
                        periodHomeless(user.getHouseMember(), lateDateList);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                        List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouse());
                        for (HouseMember spouseHouseMember : spouseHouseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(spouseHouseMember).get();
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (spouseHouseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(spouseHouseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                }
                // 배우자가 결혼 후에 팔았을 때 또는 집판날과 결혼한날이 같을 때
                else {
                    if (user.getHouse() == user.getSpouseHouse()) {
                        periodHomeless(user.getHouseMember(), lateDateList);
                        periodHomeless(user.getSpouseHouseMember(), lateDateList);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                    } else {
                        periodHomeless(user.getHouseMember(), lateDateList);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                        periodHomeless(user.getSpouseHouseMember(), lateDateList);
                        List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouse());
                        for (HouseMember spouseHouseMember : spouseHouseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(spouseHouseMember).get();
                            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (spouseHouseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(spouseHouseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                }
            }
            for(int i=0;i<lateDateList.size();i++){
                System.out.println("lateDateList##"+lateDateList.get(i));
            }
            //반환값을 가지고 늦은 순서대로 정렬
            lateDateList.sort(Collections.reverseOrder());
            LocalDate mostLateDate = lateDateList.get(0);
            //무주택기간을 기간으로 계산함
            int periodOfHomelessness = generalPrivateVerificationServiceImpl.calcAmericanAge(mostLateDate);
            for (int z = 1; z <= 15; z++) {
                if (periodOfHomelessness < z) {
                    return periodOfHomelessnessGetPoint = z * 2;
                } else return periodOfHomelessnessGetPoint = 32;
            }
        }
        return periodOfHomelessnessGetPoint;
    }

    public boolean homelessYn(HouseMember houseMember,int houseCount) {

        List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

        int flag = 0;
        int specialCase = 0;
        for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
            if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { //주거용건물유형이 오피스텥일 경우
                    specialCase++;
                    continue;
                } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { //2018.12.11 이전에 취득한 분양권일 경우
                    specialCase++;
                    continue;
                } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                    specialCase++;
                    continue;
                } else {
                    if (houseMemberProperty.getExclusiveArea() <= 60 && ((houseMemberProperty.getMetropolitanBuildingYn().equals(Yn.y) && houseMemberProperty.getAmount() <= 80000000) || (houseMemberProperty.getMetropolitanBuildingYn().equals(Yn.y) && houseMemberProperty.getAmount() <= 130000000))) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                        flag++;
                        if (specialCase <= 0)
                            continue;
                        else
                            houseCount = flag;
                        if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                            continue;
                        else
                            houseCount = flag;
                    }
                }
                houseCount++;
            }
        }
        if (houseCount == 0) // 주택수가 0일 경우 무주택세대구성원으로 판별
            return true;
        else // 아닐 경우 유주택세대구성원으로 판별, 탈락
            return false;
    }

    public Integer countOfDependents(HouseMemberRelation memberRelation, int parents, List bothParentsIsHomelessYnList) {
        System.out.println(memberRelation.getRelation());
        //무주택여부를 확인하기 위해 meetHouseHaving메소드 실행 시 true반환하면 parents++하고 houseMemberList3에 true값 저장
        int houseCount=0;

        if (homelessYn(memberRelation.getOpponent(),houseCount)) {
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
        return numberOfFamily;
    }

    public Integer numberOfFamily(User user, HouseMemberRelation houseMemberRelation, SpecialPointOfOldParentsSupportDto specialPointOfOldParentsSupport, int numberOfFamily, int parents) {
        List<Boolean> bothParentsIsHomelessYnList = new ArrayList<>();//부모님 두분다 무주택자인지 확인하는 리스트
        if (specialPointOfOldParentsSupport.getStayOverYn().equals(Yn.n) && specialPointOfOldParentsSupport.getSameResidentRegistrationYn().equals(Yn.y) && houseMemberRelation.getOpponent().getForeignerYn().equals(Yn.n)) {
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents,bothParentsIsHomelessYnList);
            }
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents,bothParentsIsHomelessYnList);
            }
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents,bothParentsIsHomelessYnList);
            }

            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents,bothParentsIsHomelessYnList);
            }
        }

        return numberOfFamily;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfDependents(User user, SpecialPointOfOldParentsSupportDto specialPointOfOldParentsSupportDto) { //부양가족 산출하기
        int numberOfDependentsGetPoint = 0;
        int parents = 0;
        int numberOfFamily = 0;
        for (int i = 0; i < specialPointOfOldParentsSupportDto.getSpecialPointOfOldParentsSupportDtoList().size(); i++) {
            SpecialPointOfOldParentsSupportDto specialPointOfOldParentsSupport = specialPointOfOldParentsSupportDto.getSpecialPointOfOldParentsSupportDtoList().get(i);
            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponentId(specialPointOfOldParentsSupport.getHouseMemberId()).get();
            if (user.getSpouseHouseMember() != null) {//배우자는 무조건 포함
                numberOfFamily++;
            }
            if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) { //배우자와 같은 세대이거나, 미혼일 경우
                if (user.getHouse().getHouseHolder() == null) {
                    throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_HOLDER);
                }
                else {
                    if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) //본인이 세대주일 때 무주택직계존속 포함
                    {
                        numberOfFamily = numberOfFamily(user, houseMemberRelation, specialPointOfOldParentsSupport,numberOfFamily,parents);
                    }
                }
            } else if (user.getHouse() != user.getSpouseHouse()) {
                if (user.getSpouseHouse().getHouseHolder() == null) {
                    throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_HOLDER);
                }
                else {
                    if (user.getSpouseHouse().getHouseHolder().getId().equals(user.getSpouseHouseMember().getId())) { //배우자가 세대주일 때 무주택직계존속 포함
                        numberOfFamily = numberOfFamily(user, houseMemberRelation, specialPointOfOldParentsSupport,numberOfFamily,parents);
                    }
                }
            } else {
                if (houseMemberRelation.getOpponent().getForeignerYn().equals(Yn.n) && ((houseMemberRelation.getRelation().getRelation().equals(Relation.손자녀) && specialPointOfOldParentsSupport.getParentsDeathYn().equals(Yn.y)) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반))) {//부모가 죽은 미혼 손자녀
                    if (houseMemberRelation.getOpponent().getMarriageDate() == null) {//미혼 자녀
                        if (specialPointOfOldParentsSupport.getDivorceYn().equals(Yn.n)) {
                            if (generalPrivateVerificationServiceImpl.calcAmericanAge(houseMemberRelation.getOpponent().getBirthDay()) < 30) {
                                if (!(specialPointOfOldParentsSupport.getNowStayOverYn().equals(Yn.y) && specialPointOfOldParentsSupport.getStayOverYn().equals(Yn.y))) {//현재 체류여부
                                    numberOfFamily++;
                                }
                            } else {
                                if (specialPointOfOldParentsSupport.getStayOverYn().equals(Yn.n)) {// 체류여부
                                    if (specialPointOfOldParentsSupport.getSameResidentRegistrationYn().equals(Yn.y)) {
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
                return numberOfDependentsGetPoint = z * 5;
            } else numberOfDependentsGetPoint = 35;
        }
        return numberOfDependentsGetPoint;
    }

    public int periodOfMonth(LocalDate joinDate) {
        LocalDate now = LocalDate.now();
        Period period = joinDate.until(now);
        return period.getYears() * 12 + period.getMonths();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bankBookVaildYn(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        Optional<Bankbook> stdBankbook = bankbookRepository.findByBankbook(optUserBankbook.get().getBankbook());
        if (stdBankbook.get().getPrivateHousingSupplyIsPossible().equals(Yn.y)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer bankbookJoinPeriod(User user) {
        int bankbookJoinPeriodGetPoint = 0;
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        int joinPeriodOfMonth = periodOfMonth(optUserBankbook.get().getJoinDate());
        int joinPeriodOfYear = generalPrivateVerificationServiceImpl.calcAmericanAge(optUserBankbook.get().getJoinDate());
        if (joinPeriodOfMonth < 12) {
            for (int z = 1; z <= 2; z++) {
                if (joinPeriodOfMonth < z * 6) {
                    return bankbookJoinPeriodGetPoint = z;
                }
            }
        } else {
            for (int z = 2; z <= 15; z++) {
                if (joinPeriodOfYear < z) {
                    return bankbookJoinPeriodGetPoint = z + 1;
                } else bankbookJoinPeriodGetPoint = 17;
            }
        }
        return bankbookJoinPeriodGetPoint;
    }
}
