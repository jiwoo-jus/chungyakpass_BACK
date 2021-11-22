package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.*;
import com.hanium.chungyakpassback.dto.point.ReadPointCalculationDto;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.*;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRepository;
import com.hanium.chungyakpassback.repository.input.UserBankbookRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.point.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationServiceImpl;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReadPointCalculationServiceImpl implements ReadPointCalculationService {
    final UserRepository userRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final UserBankbookRepository userBankbookRepository;
    final HouseMemberRepository houseMemberRepository;
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final PointCalculationOfNewMarriedServiceImpl pointCalculationOfNewMarriedServiceImpl;
    final AddressLevel1Repository addressLevel1Repository;
//    final RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried;
    final RecordGeneralMinyeongPointRepository recordGeneralMinyeongPointRepository;
    final RecordSpecialMinyeongPointOfNewMarriedRepository recordSpecialMinyeongPointOfNewMarriedRepository;
    final RecordSpecialMinyeongPointOfSingleParentsRepository recordSpecialMinyeongPointOfSingleParentsRepository;
    final RecordSpecialMinyeongPointOfMultiChildRepository recordSpecialMinyeongPointOfMultiChildRepository;
    final RecordSpecialMinyeongPointOfOldParentsSupportRepository recordSpecialMinyeongPointOfOldParentsSupportRepository;


    @Override
    public ReadPointCalculationDto readAllUserPointRecord(){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        UserPointRecordDto userPointRecordDto = new UserPointRecordDto();

        List<GeneralMinyeongResponsePointDto> generalMinyeongResponsePointDtos = new ArrayList<>();
        for (RecordGeneralMinyeongPoint recordGeneralMinyeongPoint : recordGeneralMinyeongPointRepository.findAllByUser(user)){
            GeneralMinyeongResponsePointDto generalMinyeongResponsePointDto = new GeneralMinyeongResponsePointDto(recordGeneralMinyeongPoint);
            generalMinyeongResponsePointDtos.add(generalMinyeongResponsePointDto);
        }

        List<SpecialMinyeongPointOfNewMarriedResponseDto> specialMinyeongPointOfNewMarriedResponseDtos = new ArrayList<>();
        for (RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried : recordSpecialMinyeongPointOfNewMarriedRepository.findAllByUser(user)){
            SpecialMinyeongPointOfNewMarriedResponseDto specialMinyeongPointOfNewMarriedResponseDto = new SpecialMinyeongPointOfNewMarriedResponseDto(recordSpecialMinyeongPointOfNewMarried);
            specialMinyeongPointOfNewMarriedResponseDtos.add(specialMinyeongPointOfNewMarriedResponseDto);
        }
        List<SpecialMinyeongPointOfSingleParentsResponseDto> specialMinyeongPointOfSingleParentsResponseDtos = new ArrayList<>();
        for (RecordSpecialMinyeongPointOfSingleParents recordSpecialMinyeongPointOfSingleParents : recordSpecialMinyeongPointOfSingleParentsRepository.findAllByUser(user)){
            SpecialMinyeongPointOfSingleParentsResponseDto specialMinyeongPointOfSingleParentsResponseDto = new SpecialMinyeongPointOfSingleParentsResponseDto(recordSpecialMinyeongPointOfSingleParents);
            specialMinyeongPointOfSingleParentsResponseDtos.add(specialMinyeongPointOfSingleParentsResponseDto);
        }
        List<SpecialMinyeongPointOfMultiChildResponseDto> specialMinyeongPointOfMultiChildResponseDtos = new ArrayList<>();
        for (RecordSpecialMinyeongPointOfMultiChild recordSpecialMinyeongPointOfMultiChild : recordSpecialMinyeongPointOfMultiChildRepository.findAllByUser(user)){
            SpecialMinyeongPointOfMultiChildResponseDto specialMinyeongPointOfMultiChildResponseDto = new SpecialMinyeongPointOfMultiChildResponseDto(recordSpecialMinyeongPointOfMultiChild);
            specialMinyeongPointOfMultiChildResponseDtos.add(specialMinyeongPointOfMultiChildResponseDto);
        }

        List<SpecialMinyeongPointOfOldParentsSupportResponseDto> specialMinyeongPointOfOldParentsSupportResponseDtos = new ArrayList<>();
        for (RecordSpecialMinyeongPointOfOldParentsSupport recordSpecialMinyeongPointOfOldParentsSupport : recordSpecialMinyeongPointOfOldParentsSupportRepository.findAllByUser(user)){
            SpecialMinyeongPointOfOldParentsSupportResponseDto specialMinyeongPointOfOldParentsSupportResponseDto = new SpecialMinyeongPointOfOldParentsSupportResponseDto(recordSpecialMinyeongPointOfOldParentsSupport);
            specialMinyeongPointOfOldParentsSupportResponseDtos.add(specialMinyeongPointOfOldParentsSupportResponseDto);
        }
        ReadPointCalculationDto readPointCalculationDto = ReadPointCalculationDto.builder()
                .generalMinyeongResponsePointDtos(generalMinyeongResponsePointDtos)
                .specialMinyeongPointOfNewMarriedResponseDtos(specialMinyeongPointOfNewMarriedResponseDtos)
                .specialMinyeongPointOfSingleParentsResponseDtos(specialMinyeongPointOfSingleParentsResponseDtos)
                .specialMinyeongPointOfMultiChildResponseDtos(specialMinyeongPointOfMultiChildResponseDtos)
                .specialMinyeongPointOfOldParentsSupportResponseDtos(specialMinyeongPointOfOldParentsSupportResponseDtos)
                .build();

        return readPointCalculationDto;
    }

//    @Override
//    public List<GeneralMinyeongResponsePointDto>  recordGeneralMinyeongResponsePoint(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//
//        List<GeneralMinyeongResponsePointDto> generalMinyeongResponsePointDtos = new ArrayList<>();
//        for (RecordGeneralMinyeongPoint recordGeneralMinyeongPoint : recordGeneralMinyeongPointRepository.findAllByUser(user)){
//            GeneralMinyeongResponsePointDto generalMinyeongResponsePointDto = new GeneralMinyeongResponsePointDto(recordGeneralMinyeongPoint);
//            generalMinyeongResponsePointDtos.add(generalMinyeongResponsePointDto);
//        }
//
//        return generalMinyeongResponsePointDtos;
//    }
//
//    @Override
//    public List<SpecialMinyeongPointOfNewMarriedResponseDto> recordSpecialMinyeongPointOfNewMarried(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//
//        List<SpecialMinyeongPointOfNewMarriedResponseDto> specialMinyeongPointOfNewMarriedResponseDtos = new ArrayList<>();
//        for (RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried : recordSpecialMinyeongPointOfNewMarriedRepository.findAllByUser(user)){
//            SpecialMinyeongPointOfNewMarriedResponseDto specialMinyeongPointOfNewMarriedResponseDto = new SpecialMinyeongPointOfNewMarriedResponseDto(recordSpecialMinyeongPointOfNewMarried);
//            specialMinyeongPointOfNewMarriedResponseDtos.add(specialMinyeongPointOfNewMarriedResponseDto);
//        }
//
//        return specialMinyeongPointOfNewMarriedResponseDtos;
//    }
//
//    @Override
//    public  List<SpecialMinyeongPointOfSingleParentsResponseDto> recordSpecialMinyeongPointOfSingleParents(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//
//        List<SpecialMinyeongPointOfSingleParentsResponseDto> specialMinyeongPointOfSingleParentsResponseDtos = new ArrayList<>();
//        for (RecordSpecialMinyeongPointOfSingleParents recordSpecialMinyeongPointOfSingleParents : recordSpecialMinyeongPointOfSingleParentsRepository.findAllByUser(user)){
//            SpecialMinyeongPointOfSingleParentsResponseDto specialMinyeongPointOfSingleParentsResponseDto = new SpecialMinyeongPointOfSingleParentsResponseDto(recordSpecialMinyeongPointOfSingleParents);
//            specialMinyeongPointOfSingleParentsResponseDtos.add(specialMinyeongPointOfSingleParentsResponseDto);
//        }
//
//        return specialMinyeongPointOfSingleParentsResponseDtos;
//    }
//
//    @Override
//    public  List<SpecialMinyeongPointOfMultiChildResponseDto> recordSpecialMinyeongPointOfMultiChild(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//
//        List<SpecialMinyeongPointOfMultiChildResponseDto> specialMinyeongPointOfMultiChildResponseDtos = new ArrayList<>();
//        for (RecordSpecialMinyeongPointOfMultiChild recordSpecialMinyeongPointOfMultiChild : recordSpecialMinyeongPointOfMultiChildRepository.findAllByUser(user)){
//            SpecialMinyeongPointOfMultiChildResponseDto specialMinyeongPointOfMultiChildResponseDto = new SpecialMinyeongPointOfMultiChildResponseDto(recordSpecialMinyeongPointOfMultiChild);
//            specialMinyeongPointOfMultiChildResponseDtos.add(specialMinyeongPointOfMultiChildResponseDto);
//        }
//
//        return specialMinyeongPointOfMultiChildResponseDtos;
//    }
//
//    @Override
//    public List<SpecialMinyeongPointOfOldParentsSupportResponseDto> recordSpecialMinyeongPointOfOldParentsSupport(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//
//        List<SpecialMinyeongPointOfOldParentsSupportResponseDto> specialMinyeongPointOfOldParentsSupportResponseDtos = new ArrayList<>();
//        for (RecordSpecialMinyeongPointOfOldParentsSupport recordSpecialMinyeongPointOfOldParentsSupport : recordSpecialMinyeongPointOfOldParentsSupportRepository.findAllByUser(user)){
//            SpecialMinyeongPointOfOldParentsSupportResponseDto specialMinyeongPointOfOldParentsSupportResponseDto = new SpecialMinyeongPointOfOldParentsSupportResponseDto(recordSpecialMinyeongPointOfOldParentsSupport);
//            specialMinyeongPointOfOldParentsSupportResponseDtos.add(specialMinyeongPointOfOldParentsSupportResponseDto);
//        }
//        return specialMinyeongPointOfOldParentsSupportResponseDtos;
//    }
//



}
