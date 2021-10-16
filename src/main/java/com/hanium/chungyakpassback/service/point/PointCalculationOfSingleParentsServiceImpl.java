package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationServiceImpl;
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
        return pointCalculationOfNewMarriedServiceImpl.periodOfApplicableAreaResidence(user,aptInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer ageOfMostYoungChild(User user) {
        Integer mostYoungChildAgeGetPoint = 0;
        List<LocalDate> minorsBirthDateList = new ArrayList<>();
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList) {
            if (houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반)){
                minorsBirthDateList.add(houseMemberRelation.getOpponent().getBirthDay());
            }
        }
        minorsBirthDateList.sort(Collections.reverseOrder());
        if(minorsBirthDateList.size()==0){
            throw new CustomException(ErrorCode.NOT_FOUND_Child);
        }
        else {
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
