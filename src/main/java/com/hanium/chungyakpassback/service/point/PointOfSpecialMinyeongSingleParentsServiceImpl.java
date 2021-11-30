package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongSingleParentsDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongSingleParentsResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongSingleParents;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.point.PointOfSpecialMinyeongSingleParentsRepository;
import com.hanium.chungyakpassback.service.verification.VerificationOfGeneralMinyeongServiceImpl;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointOfSpecialMinyeongSingleParentsServiceImpl implements PointOfSpecialMinyeongSingleParentsService {
    final PointOfSpecialMinyeongNewlyMarriedServiceImpl pointCalculationOfNewMarriedServiceImpl;
    final VerificationOfGeneralMinyeongServiceImpl generalPrivateVerificationServiceImpl;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final UserRepository userRepository;
    final AptInfoRepository aptInfoRepository;
    final PointOfSpecialMinyeongSingleParentsRepository pointOfSpecialMinyeongSingleParentsRepository;

    @Override
    public List<PointOfSpecialMinyeongSingleParentsResponseDto> readSingleParentsPointCalculations() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<PointOfSpecialMinyeongSingleParentsResponseDto> pointOfSpecialMinyeongSingleParentsResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongSingleParents pointOfSpecialMinyeongSingleParents : pointOfSpecialMinyeongSingleParentsRepository.findAllByUser(user)) {
            PointOfSpecialMinyeongSingleParentsResponseDto pointOfSpecialMinyeongSingleParentsResponseDto = new PointOfSpecialMinyeongSingleParentsResponseDto(pointOfSpecialMinyeongSingleParents);
            pointOfSpecialMinyeongSingleParentsResponseDtos.add(pointOfSpecialMinyeongSingleParentsResponseDto);
        }

        return pointOfSpecialMinyeongSingleParentsResponseDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PointOfSpecialMinyeongSingleParentsResponseDto createSingleParentsPointCalculation(PointOfSpecialMinyeongSingleParentsDto pointOfSpecialMinyeongSingleParentsDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(pointOfSpecialMinyeongSingleParentsDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        Integer numberOfMinors = numberOfMinors(user);
        Integer ageOfMostYoungChild = ageOfMostYoungChild(user);
        Integer bankbookPaymentsCount = bankbookPaymentsCount(user);
        Integer periodOfApplicableAreaResidence = periodOfApplicableAreaResidence(user, aptInfo);
        Integer monthOfAverageIncome = monthOfAverageIncome(user);
        Integer total = numberOfMinors + ageOfMostYoungChild + bankbookPaymentsCount + periodOfApplicableAreaResidence + monthOfAverageIncome;

        PointOfSpecialMinyeongSingleParents pointOfSpecialMinyeongSingleParents = new PointOfSpecialMinyeongSingleParents(user, aptInfo, numberOfMinors, ageOfMostYoungChild, bankbookPaymentsCount, periodOfApplicableAreaResidence, monthOfAverageIncome, total);
        pointOfSpecialMinyeongSingleParentsRepository.save(pointOfSpecialMinyeongSingleParents);

        return new PointOfSpecialMinyeongSingleParentsResponseDto(pointOfSpecialMinyeongSingleParents);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfMinors(User user) {//영유아자녀수 가점
        return pointCalculationOfNewMarriedServiceImpl.numberOfMinors(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer bankbookPaymentsCount(User user) { //청약통장 납입횟수 가점
        return pointCalculationOfNewMarriedServiceImpl.bankbookPaymentsCount(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) { //해당지역 거주기간 가점
        return pointCalculationOfNewMarriedServiceImpl.periodOfApplicableAreaResidence(user, aptInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer ageOfMostYoungChild(User user) { //자녀나이 가점
        Integer mostYoungChildAgeGetPoint = 0;
        List<LocalDate> minorsBirthDateList = new ArrayList<>();
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList) {
            if (houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반)) {
                minorsBirthDateList.add(houseMemberRelation.getOpponent().getBirthDay());
            }
        }
        minorsBirthDateList.sort(Collections.reverseOrder());
        if (minorsBirthDateList.size() == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND_Child);
        } else {
            int mostYoungChildAge = generalPrivateVerificationServiceImpl.calcAmericanAge(minorsBirthDateList.get(0));
            for (int u = 0; u <= 2; u++) {
                if (mostYoungChildAge < 3 + 2 * u) {
                    return mostYoungChildAgeGetPoint = 3 - u;
                }
            }
        }
        return mostYoungChildAgeGetPoint;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer monthOfAverageIncome(User user) {
        return pointCalculationOfNewMarriedServiceImpl.monthOfAverageIncome(user);
    }

}
