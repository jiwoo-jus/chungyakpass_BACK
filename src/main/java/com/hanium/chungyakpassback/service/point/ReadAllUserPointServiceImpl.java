package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.*;
import com.hanium.chungyakpassback.dto.point.ReadAllUserPointDto;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.*;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRepository;
import com.hanium.chungyakpassback.repository.input.UserBankbookRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.point.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.service.verification.VerificationOfGeneralMinyeongServiceImpl;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReadAllUserPointServiceImpl implements ReadAllUserPointService {
    final UserRepository userRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final UserBankbookRepository userBankbookRepository;
    final HouseMemberRepository houseMemberRepository;
    final VerificationOfGeneralMinyeongServiceImpl generalPrivateVerificationServiceImpl;
    final PointOfSpecialMinyeongNewlyMarriedServiceImpl pointCalculationOfNewMarriedServiceImpl;
    final AddressLevel1Repository addressLevel1Repository;
    final PointOfGeneralMinyeongRepository pointOfGeneralMinyeongRepository;
    final PointOfSpecialMinyeongNewlyMarriedRepository pointOfSpecialMinyeongNewlyMarriedRepository;
    final PointOfSpecialMinyeongSingleParentsRepository pointOfSpecialMinyeongSingleParentsRepository;
    final PointOfSpecialMinyeongMultiChildRepository pointOfSpecialMinyeongMultiChildRepository;
    final PointOfSpecialMinyeongOldParentsSupportRepository pointOfSpecialMinyeongOldParentsSupportRepository;


    @Override
    public ReadAllUserPointDto readAllUserPointRecord(){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<PointOfGeneralMinyeongResponseDto> pointOfGeneralMinyeongResponseDtos = new ArrayList<>();
        for (PointOfGeneralMinyeong pointOfGeneralMinyeong : pointOfGeneralMinyeongRepository.findAllByUser(user)){
            PointOfGeneralMinyeongResponseDto pointOfGeneralMinyeongResponseDto = new PointOfGeneralMinyeongResponseDto(pointOfGeneralMinyeong);
            pointOfGeneralMinyeongResponseDtos.add(pointOfGeneralMinyeongResponseDto);
        }

        List<PointOfSpecialMinyeongNewlyMarriedResponseDto> pointOfSpecialMinyeongNewlyMarriedResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongNewlyMarried pointOfSpecialMinyeongNewlyMarried : pointOfSpecialMinyeongNewlyMarriedRepository.findAllByUser(user)){
            PointOfSpecialMinyeongNewlyMarriedResponseDto pointOfSpecialMinyeongNewlyMarriedResponseDto = new PointOfSpecialMinyeongNewlyMarriedResponseDto(pointOfSpecialMinyeongNewlyMarried);
            pointOfSpecialMinyeongNewlyMarriedResponseDtos.add(pointOfSpecialMinyeongNewlyMarriedResponseDto);
        }
        List<PointOfSpecialMinyeongSingleParentsResponseDto> pointOfSpecialMinyeongSingleParentsResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongSingleParents pointOfSpecialMinyeongSingleParents : pointOfSpecialMinyeongSingleParentsRepository.findAllByUser(user)){
            PointOfSpecialMinyeongSingleParentsResponseDto pointOfSpecialMinyeongSingleParentsResponseDto = new PointOfSpecialMinyeongSingleParentsResponseDto(pointOfSpecialMinyeongSingleParents);
            pointOfSpecialMinyeongSingleParentsResponseDtos.add(pointOfSpecialMinyeongSingleParentsResponseDto);
        }
        List<PointOfSpecialMinyeongMultiChildResponseDto> pointOfSpecialMinyeongMultiChildResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongMultiChild pointOfSpecialMinyeongMultiChild : pointOfSpecialMinyeongMultiChildRepository.findAllByUser(user)){
            PointOfSpecialMinyeongMultiChildResponseDto pointOfSpecialMinyeongMultiChildResponseDto = new PointOfSpecialMinyeongMultiChildResponseDto(pointOfSpecialMinyeongMultiChild);
            pointOfSpecialMinyeongMultiChildResponseDtos.add(pointOfSpecialMinyeongMultiChildResponseDto);
        }

        List<PointOfSpecialMinyeongOldParentsSupportResponseDto> pointOfSpecialMinyeongOldParentsSupportResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongOldParentsSupport pointOfSpecialMinyeongOldParentsSupport : pointOfSpecialMinyeongOldParentsSupportRepository.findAllByUser(user)){
            PointOfSpecialMinyeongOldParentsSupportResponseDto pointOfSpecialMinyeongOldParentsSupportResponseDto = new PointOfSpecialMinyeongOldParentsSupportResponseDto(pointOfSpecialMinyeongOldParentsSupport);
            pointOfSpecialMinyeongOldParentsSupportResponseDtos.add(pointOfSpecialMinyeongOldParentsSupportResponseDto);
        }
        ReadAllUserPointDto readAllUserPointDto = ReadAllUserPointDto.builder()
                .pointOfGeneralMinyeongResponseDtos(pointOfGeneralMinyeongResponseDtos)
                .pointOfSpecialMinyeongNewlyMarriedResponseDtos(pointOfSpecialMinyeongNewlyMarriedResponseDtos)
                .pointOfSpecialMinyeongSingleParentsResponseDtos(pointOfSpecialMinyeongSingleParentsResponseDtos)
                .pointOfSpecialMinyeongMultiChildResponseDtos(pointOfSpecialMinyeongMultiChildResponseDtos)
                .pointOfSpecialMinyeongOldParentsSupportResponseDtos(pointOfSpecialMinyeongOldParentsSupportResponseDtos)
                .build();

        return readAllUserPointDto;
    }





}
