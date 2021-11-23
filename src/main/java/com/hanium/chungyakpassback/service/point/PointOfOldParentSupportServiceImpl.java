package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongOldParentsSupportResponseDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialOldParentsSupportDto;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongOldParentsSupport;
import com.hanium.chungyakpassback.entity.standard.Bankbook;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.ResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.point.PointOfSpecialMinyeongOldParentsSupportRepository;
import com.hanium.chungyakpassback.repository.standard.BankbookRepository;
import com.hanium.chungyakpassback.service.verification.VerificationOfGeneralMinyeongServiceImpl;
import com.hanium.chungyakpassback.util.SecurityUtil;
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
public class PointOfOldParentSupportServiceImpl implements PointOfOldParentSupportService {
    final HouseMemberRepository houseMemberRepository;
    final BankbookRepository bankbookRepository;
    final HouseRepository houseRepository;
    final UserBankbookRepository userBankbookRepository;
    final VerificationOfGeneralMinyeongServiceImpl generalPrivateVerificationServiceImpl;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final PointOfSpecialMinyeongOldParentsSupportRepository pointOfSpecialMinyeongOldParentsSupportRepository;
    final UserRepository userRepository;

    @Override
    public List<PointOfSpecialMinyeongOldParentsSupportResponseDto> readOldParentsSupportPointCalculations() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<PointOfSpecialMinyeongOldParentsSupportResponseDto> pointOfSpecialMinyeongOldParentsSupportResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongOldParentsSupport pointOfSpecialMinyeongOldParentsSupport : pointOfSpecialMinyeongOldParentsSupportRepository.findAllByUser(user)) {
            PointOfSpecialMinyeongOldParentsSupportResponseDto pointOfSpecialMinyeongOldParentsSupportResponseDto = new PointOfSpecialMinyeongOldParentsSupportResponseDto(pointOfSpecialMinyeongOldParentsSupport);
            pointOfSpecialMinyeongOldParentsSupportResponseDtos.add(pointOfSpecialMinyeongOldParentsSupportResponseDto);
        }
        return pointOfSpecialMinyeongOldParentsSupportResponseDtos;
    }

    @Override
    public PointOfSpecialMinyeongOldParentsSupportResponseDto createOldParentsSupportPointCalculation(PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        Long houseMemberId = pointOfSpecialOldParentsSupportDto.getHouseMemberId();
        Yn parentsDeathYn = pointOfSpecialOldParentsSupportDto.getParentsDeathYn();
        Yn divorceYn = pointOfSpecialOldParentsSupportDto.getDivorceYn();
        Yn sameResidentRegistrationYn = pointOfSpecialOldParentsSupportDto.getSameResidentRegistrationYn();
        Yn stayOverYn = pointOfSpecialOldParentsSupportDto.getStayOverYn();
        Yn nowStayOverYn = pointOfSpecialOldParentsSupportDto.getNowStayOverYn();
        Integer periodOfHomelessness = periodOfHomelessness(user);
        Integer bankbookJoinPeriod = bankbookJoinPeriod(user);
        Integer numberOfDependents = numberOfDependents(user, pointOfSpecialOldParentsSupportDto);
        boolean bankBookVaildYn = bankBookVaildYn(user);
        Integer total = periodOfHomelessness + bankbookJoinPeriod + numberOfDependents;

        PointOfSpecialMinyeongOldParentsSupport pointOfSpecialMinyeongOldParentsSupport = new PointOfSpecialMinyeongOldParentsSupport(user, houseMemberId, parentsDeathYn, divorceYn, sameResidentRegistrationYn, stayOverYn, nowStayOverYn, periodOfHomelessness, bankbookJoinPeriod, numberOfDependents, bankBookVaildYn, total);
        pointOfSpecialMinyeongOldParentsSupportRepository.save(pointOfSpecialMinyeongOldParentsSupport);

        return new PointOfSpecialMinyeongOldParentsSupportResponseDto(pointOfSpecialMinyeongOldParentsSupport);
    }

    //세대구성원별 무주택기간을 구하는 메소드
    public List periodHomeless(HouseMember houseMember, List lateDateList, LocalDate lateDate) {

        //30세이상이거나 결혼한경우
        if ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) >= 30) || houseMember.getMarriageDate() != null) {
            LocalDate birthDayAfter30Year = houseMember.getBirthDay().plusYears(30);//세대구성원의 30세 생일


            //(1)만30세이상 미혼이거나 만30세 이후 혼인신고 시 만 30세 생일과 무주택이 된 날짜중에 늦은 날
            if (houseMember.getMarriageDate() == null || birthDayAfter30Year.isBefore(houseMember.getMarriageDate())) {
                if (birthDayAfter30Year.isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = birthDayAfter30Year;
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
            //만30세 이전 혼인신고시 혼인신고일과 무주택된 날중 늦은 날짜
            else if (birthDayAfter30Year.isAfter(houseMember.getMarriageDate())) {
                if (houseMember.getMarriageDate().isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = houseMember.getMarriageDate();
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
            lateDateList.add(lateDate);
        }
        for (int i = 0; i < lateDateList.size(); i++) {
            System.out.println("lateDateList" + lateDateList.get(i));
        }
        return lateDateList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfHomelessness(User user) {
        int periodOfHomelessnessGetPoint = 0;
        int houseCount = 0;
        LocalDate lateDate = null;//무주택기간
        List<LocalDate> lateDateList = new ArrayList<>(); //무주택시작일을 담아놓은 리스트
        if (user.getSpouseHouseMember() != null && user.getSpouseHouseMember().getMarriageDate() == null) {//배우자가 있는데 혼인신고일이 없으면 에러발생
            throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);
        } else {
            //신청자가 30세미만에 미혼이거나 외국인이면 0점
            if ((generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getBirthDay()) < 30 && user.getSpouseHouseMember() == null) || user.getHouseMember().getForeignerYn().equals(Yn.y)) {
                return periodOfHomelessnessGetPoint = 0;
            } else {
                //배우자가 없거나 배우자가 결혼전에 무주택자가 된경우
                if (user.getSpouseHouseMember() == null || (user.getSpouseHouseMember().getMarriageDate().isAfter(user.getSpouseHouseMember().getHomelessStartDate()))) {

                    //배우자가 없거나 배우자와 같이 거주중이면 신청자와 신청자의 직계존속의 무주택기간을 산출
                    if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouseMember() == null) {
                        periodHomeless(user.getHouseMember(), lateDateList, lateDate);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            }
                            //신청자의 직계존속이 집을 소유한 적이 있으면 무주택산정 리스트에 추가한다.
                            else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                    //배우자와 분리세대면 신청자와 신청자의 직계존속 배우자의 직계존속의 무주택기간을 구한다.
                    else {
                        periodHomeless(user.getHouseMember(), lateDateList, lateDate);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            } else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                        List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouse());
                        for (HouseMember spouseHouseMember : spouseHouseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(spouseHouseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            } else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (spouseHouseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(spouseHouseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                }
                // 배우자가 결혼 후에 팔았을 때 또는 집판날과 결혼한날이 같을 때
                else {
                    //배우자와 신청자와 동일 세대인 경우 신청자와 신청자의 직계존속, 배우자의 무주택기간을 구한다.
                    if (user.getHouse() == user.getSpouseHouse()) {
                        periodHomeless(user.getHouseMember(), lateDateList, lateDate);
                        periodHomeless(user.getSpouseHouseMember(), lateDateList, lateDate);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            } else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                    //배우자와 신청자와 분리 세대인 경우 신청자와 신청자의 직계존속, 배우자와 배우자 직계존속의 무주택기간을 구한다.
                    else {
                        periodHomeless(user.getHouseMember(), lateDateList, lateDate);
                        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouse());
                        for (HouseMember houseMember : houseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(houseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            } else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (houseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(houseMember.getHomelessStartDate());
                                }
                            }
                        }
                        periodHomeless(user.getSpouseHouseMember(), lateDateList, lateDate);
                        List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouse());
                        for (HouseMember spouseHouseMember : spouseHouseMemberList) {
                            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponent(spouseHouseMember).get();
                            if (homelessYn(houseMemberRelation.getOpponent(), houseCount) == false) {
                                return periodOfHomelessnessGetPoint = 0;
                            } else if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                                if (spouseHouseMember.getHomelessStartDate() != null) {
                                    lateDateList.add(spouseHouseMember.getHomelessStartDate());
                                }
                            }
                        }
                    }
                }
            }
            //반환값을 가지고 늦은 순서대로 정렬
            lateDateList.sort(Collections.reverseOrder());
            LocalDate mostLateDate = lateDateList.get(0);
            //무주택기간을 기간으로 계산함
            int periodOfHomelessness = generalPrivateVerificationServiceImpl.calcAmericanAge(mostLateDate);
            for (int z = 1; z <= 15; z++) {
                if (periodOfHomelessness < z) {
                    return periodOfHomelessnessGetPoint = z * 2;
                } else
                    periodOfHomelessnessGetPoint = 32;
            }
        }
        return periodOfHomelessnessGetPoint;
    }

    //무주택여부
    public boolean homelessYn(HouseMember houseMember, int houseCount) {

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
                    //예외주택에 해당하는 경우
                } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                    specialCase++;
                    continue;
                } else {
                    //면적60이하 수도권 8000만원이하 비수도권 1억3천 이하의 소형주택 일시 예외적용
                    if (houseMemberProperty.getExclusiveArea() <= 60 && ((houseMemberProperty.getMetropolitanBuildingYn().equals(Yn.y) && houseMemberProperty.getAmount() <= 80000000) || (houseMemberProperty.getMetropolitanBuildingYn().equals(Yn.y) && houseMemberProperty.getAmount() <= 130000000))) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                        flag++;
                        if (specialCase <= 0)
                            continue;
                        else//예외주택이 하나라도 있으면 count
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
        //무주택여부를 확인하기 위해 meetHouseHaving메소드 실행 시 true반환하면 parents++하고 houseMemberList3에 true값 저장
        int houseCount = 0;

        //무주택자일경우 부양가족으로 추가한다.
        if (homelessYn(memberRelation.getOpponent(), houseCount)) {
            parents++;
            bothParentsIsHomelessYnList.add(Boolean.TRUE);
        } else {//아니면 flase저장
            bothParentsIsHomelessYnList.add(Boolean.FALSE);
        }

        int numberOfFamily;
        //부모 두분중 한분이라도 유주택자일 경우 두분다 부양가족에서 제외한다.
        if (bothParentsIsHomelessYnList.contains(Boolean.FALSE)) {
            numberOfFamily = 0;
        } else {
            //아니면 부양가족으로 포함시킨다.
            numberOfFamily = parents;
        }
        return numberOfFamily;
    }

    public Integer numberOfFamily(User user, HouseMemberRelation houseMemberRelation, PointOfSpecialOldParentsSupportDto specialPointOfOldParentsSupport, int numberOfFamily, int parents, List bothParentsIsHomelessYnList) {
        if (specialPointOfOldParentsSupport.getStayOverYn().equals(Yn.n) && specialPointOfOldParentsSupport.getSameResidentRegistrationYn().equals(Yn.y) && houseMemberRelation.getOpponent().getForeignerYn().equals(Yn.n)) {
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents, bothParentsIsHomelessYnList);
            }
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents, bothParentsIsHomelessYnList);
            }
            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents, bothParentsIsHomelessYnList);
            }

            if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모)) && houseMemberRelation.getUser().equals(user)) {
                numberOfFamily = numberOfFamily + countOfDependents(houseMemberRelation, parents, bothParentsIsHomelessYnList);
            }
        }

        return numberOfFamily;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfDependents(User user, PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto) { //부양가족 산출하기
        int numberOfDependentsGetPoint = 0;
        int parents = 0;
        int numberOfFamily = 0;
        List<Boolean> bothParentsIsHomelessYnList = new ArrayList<>();//부모님 두분다 무주택자인지 확인하는 리스트


        if (user.getSpouseHouseMember() != null) {//배우자는 무조건 포함
            numberOfFamily++;
        }

        for (int i = 0; i < pointOfSpecialOldParentsSupportDto.getPointOfSpecialOldParentsSupportDtoList().size(); i++) {
            PointOfSpecialOldParentsSupportDto specialPointOfOldParentsSupport = pointOfSpecialOldParentsSupportDto.getPointOfSpecialOldParentsSupportDtoList().get(i);
            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByOpponentId(specialPointOfOldParentsSupport.getHouseMemberId()).get();

            if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) { //배우자와 같은 세대이거나, 미혼일 경우
                if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) //본인이 세대주일 때 무주택직계존속 포함
                {
                    numberOfFamily = numberOfFamily(user, houseMemberRelation, specialPointOfOldParentsSupport, numberOfFamily, parents, bothParentsIsHomelessYnList);

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
            } else if (user.getHouse() != user.getSpouseHouse()) {
                if (user.getSpouseHouse().getHouseHolder().getId().equals(user.getSpouseHouseMember().getId())) { //배우자가 세대주일 때 무주택직계존속 포함
                    numberOfFamily = numberOfFamily(user, houseMemberRelation, specialPointOfOldParentsSupport, numberOfFamily, parents, bothParentsIsHomelessYnList);

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

        }

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

    public int periodOfYear(LocalDate joinDate) {
        LocalDate now = LocalDate.now();
        int periodOfYear = now.minusYears(joinDate.getYear()).getYear();

        if (joinDate.plusYears(periodOfYear).isAfter(now)) // 생일이 지났는지 여부를 판단
            periodOfYear = periodOfYear - 1;
        return periodOfYear;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bankBookVaildYn(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
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
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        int joinPeriodOfMonth = periodOfMonth(optUserBankbook.get().getJoinDate());
        int joinPeriodOfYear = periodOfYear(optUserBankbook.get().getJoinDate());
        if (joinPeriodOfMonth < 12) {
            for (int z = 1; z <= 2; z++) {
                if (joinPeriodOfMonth < z * 6) {
                    bankbookJoinPeriodGetPoint = z;
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
