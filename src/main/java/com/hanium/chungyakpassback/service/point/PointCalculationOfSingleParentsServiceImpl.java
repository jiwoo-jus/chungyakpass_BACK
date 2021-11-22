package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.SpecialMinyeongPointOfSingleParentsDto;
import com.hanium.chungyakpassback.dto.point.SpecialMinyeongPointOfSingleParentsResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfSingleParents;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.point.RecordSpecialMinyeongPointOfSingleParentsRepository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationServiceImpl;
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
public class PointCalculationOfSingleParentsServiceImpl implements PointCalculationOfSingleParentsService {
    final PointCalculationOfNewMarriedServiceImpl pointCalculationOfNewMarriedServiceImpl;
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final UserRepository userRepository;
    final AptInfoRepository aptInfoRepository;
    final RecordSpecialMinyeongPointOfSingleParentsRepository recordSpecialMinyeongPointOfSingleParentsRepository;

    @Override
    public List<SpecialMinyeongPointOfSingleParentsResponseDto> readSingleParentsPointCalculations() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialMinyeongPointOfSingleParentsResponseDto> specialMinyeongPointOfSingleParentsResponseDtos = new ArrayList<>();
        for (RecordSpecialMinyeongPointOfSingleParents recordSpecialMinyeongPointOfSingleParents : recordSpecialMinyeongPointOfSingleParentsRepository.findAllByUser(user)) {
            SpecialMinyeongPointOfSingleParentsResponseDto specialMinyeongPointOfSingleParentsResponseDto = new SpecialMinyeongPointOfSingleParentsResponseDto(recordSpecialMinyeongPointOfSingleParents);
            specialMinyeongPointOfSingleParentsResponseDtos.add(specialMinyeongPointOfSingleParentsResponseDto);
        }

        return specialMinyeongPointOfSingleParentsResponseDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpecialMinyeongPointOfSingleParentsResponseDto createSingleParentsPointCalculation(SpecialMinyeongPointOfSingleParentsDto specialMinyeongPointOfSingleParentsDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialMinyeongPointOfSingleParentsDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        Integer numberOfMinors = numberOfMinors(user);
        Integer ageOfMostYoungChild = ageOfMostYoungChild(user);
        Integer bankbookPaymentsCount = bankbookPaymentsCount(user);
        Integer periodOfApplicableAreaResidence = periodOfApplicableAreaResidence(user, aptInfo);
        Integer monthOfAverageIncome = monthOfAverageIncome(user);
        Integer total = numberOfMinors + ageOfMostYoungChild + bankbookPaymentsCount + periodOfApplicableAreaResidence + monthOfAverageIncome;

        RecordSpecialMinyeongPointOfSingleParents recordSpecialMinyeongPointOfSingleParents = new RecordSpecialMinyeongPointOfSingleParents(user, aptInfo, numberOfMinors, ageOfMostYoungChild, bankbookPaymentsCount, periodOfApplicableAreaResidence, monthOfAverageIncome, total);
        recordSpecialMinyeongPointOfSingleParentsRepository.save(recordSpecialMinyeongPointOfSingleParents);

        return new SpecialMinyeongPointOfSingleParentsResponseDto(recordSpecialMinyeongPointOfSingleParents);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfMinors(User user) {
        return pointCalculationOfNewMarriedServiceImpl.numberOfMinors(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer bankbookPaymentsCount(User user) {
        return pointCalculationOfNewMarriedServiceImpl.bankbookPaymentsCount(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) {
        return pointCalculationOfNewMarriedServiceImpl.periodOfApplicableAreaResidence(user, aptInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer ageOfMostYoungChild(User user) {
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
