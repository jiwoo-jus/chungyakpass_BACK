package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.point.GeneralMinyeongResponsePointDto;
import com.hanium.chungyakpassback.dto.point.SpecialMinyeongPointOfNewMarriedResponseDto;
import com.hanium.chungyakpassback.dto.point.SpecialMinyeongPointOfSingleParentsResponseDto;
import com.hanium.chungyakpassback.dto.record.UserVerificationRecordDto;
import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.*;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.record.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VerificationRecordServiceImpl implements VerificationRecordService {

    final UserRepository userRepository;
    final VerificationRecordGeneralMinyeongRepository verificationRecordGeneralMinyeongRepository;
    final VerificationRecordGeneralKookminRepository verificationRecordGeneralKookminRepository;
    final VerificationRecordSpecialMinyeongMultiChildRepository verificationRecordSpecialMinyeongMultiChildRepository;
    final VerificationRecordSpecialKookminMultiChildRepository verificationRecordSpecialKookminMultiChildRepository;
    final VerificationRecordSpecialMinyeongOldParentRepository verificationRecordSpecialMinyeongOldParentRepository;
    final VerificationRecordSpecialKookminOldParentRepository verificationRecordSpecialKookminOldParentRepository;
    final VerificationRecordSpecialMinyeongNewlyMarriedRepository verificationRecordSpecialMinyeongNewlyMarriedRepository;
    final VerificationRecordSpecialKookminNewlyMarriedRepository verificationRecordSpecialKookminNewlyMarriedRepository;
    final VerificationRecordSpecialMinyeongFirstLifeRepository verificationRecordSpecialMinyeongFirstLifeRepository;
    final VerificationRecordSpecialKookminFirstLifeRepository verificationRecordSpecialKookminFirstLifeRepository;

    @Override
    public UserVerificationRecordDto readAllUserVerificationRecord() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<GeneralMinyeongResponseDto> generalMinyeongResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong : verificationRecordGeneralMinyeongRepository.findAllByUser(user)) {
            GeneralMinyeongResponseDto generalMinyeongResponseDto = new GeneralMinyeongResponseDto(verificationRecordGeneralMinyeong);
            generalMinyeongResponseDtos.add(generalMinyeongResponseDto);
        }

        List<GeneralKookminResponseDto> generalKookminResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralKookmin verificationRecordGeneralKookmin : verificationRecordGeneralKookminRepository.findAllByUser(user)) {
            GeneralKookminResponseDto generalKookminResponseDto = new GeneralKookminResponseDto(verificationRecordGeneralKookmin);
            generalKookminResponseDtos.add(generalKookminResponseDto);
        }

        List<SpecialMinyeongMultiChildResponseDto> specialMinyeongMultiChildResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongMultiChild verificationRecordSpecialMinyeongMultiChild : verificationRecordSpecialMinyeongMultiChildRepository.findAllByUser(user)) {
            SpecialMinyeongMultiChildResponseDto specialMinyeongMultiChildResponseDto = new SpecialMinyeongMultiChildResponseDto(verificationRecordSpecialMinyeongMultiChild);
            specialMinyeongMultiChildResponseDtos.add(specialMinyeongMultiChildResponseDto);
        }

        List<SpecialKookminPublicMultiChildResponseDto> specialKookminPublicMultiChildResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminMultiChild verificationRecordSpecialKookminMultiChild : verificationRecordSpecialKookminMultiChildRepository.findAllByUser(user)) {
            SpecialKookminPublicMultiChildResponseDto specialKookminPublicMultiChildResponseDto = new SpecialKookminPublicMultiChildResponseDto(verificationRecordSpecialKookminMultiChild);
            specialKookminPublicMultiChildResponseDtos.add(specialKookminPublicMultiChildResponseDto);
        }

        List<SpecialMinyeongOldParentResponseDto> specialMinyeongOldParentResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongOldParent verificationRecordSpecialMinyeongOldParent : verificationRecordSpecialMinyeongOldParentRepository.findAllByUser(user)) {
            SpecialMinyeongOldParentResponseDto specialMinyeongOldParentResponseDto = new SpecialMinyeongOldParentResponseDto(verificationRecordSpecialMinyeongOldParent);
            specialMinyeongOldParentResponseDtos.add(specialMinyeongOldParentResponseDto);
        }

        List<SpecialKookminPublicOldParentResponseDto> specialKookminPublicOldParentResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminOldParent verificationRecordSpecialKookminOldParent : verificationRecordSpecialKookminOldParentRepository.findAllByUser(user)) {
            SpecialKookminPublicOldParentResponseDto specialKookminPublicOldParentResponseDto = new SpecialKookminPublicOldParentResponseDto(verificationRecordSpecialKookminOldParent);
            specialKookminPublicOldParentResponseDtos.add(specialKookminPublicOldParentResponseDto);
        }

        List<SpecialMinyeongNewlyMarriedResponseDto> specialMinyeongNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongNewlyMarried verificationRecordSpecialMinyeongNewlyMarried : verificationRecordSpecialMinyeongNewlyMarriedRepository.findAllByUser(user)) {
            SpecialMinyeongNewlyMarriedResponseDto specialMinyeongNewlyMarriedResponseDto = new SpecialMinyeongNewlyMarriedResponseDto(verificationRecordSpecialMinyeongNewlyMarried);
            specialMinyeongNewlyMarriedResponseDtos.add(specialMinyeongNewlyMarriedResponseDto);
        }

        List<SpecialKookminPublicNewlyMarriedResponseDto> specialKookminPublicNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminNewlyMarried verificationRecordSpecialKookminNewlyMarried : verificationRecordSpecialKookminNewlyMarriedRepository.findAllByUser(user)) {
            SpecialKookminPublicNewlyMarriedResponseDto specialKookminPublicNewlyMarriedResponseDto = new SpecialKookminPublicNewlyMarriedResponseDto(verificationRecordSpecialKookminNewlyMarried);
            specialKookminPublicNewlyMarriedResponseDtos.add(specialKookminPublicNewlyMarriedResponseDto);
        }

        List<SpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongFirstLife verificationRecordSpecialMinyeongFirstLife : verificationRecordSpecialMinyeongFirstLifeRepository.findAllByUser(user)) {
            SpecialMinyeongFirstLifeResponseDto specialMinyeongFirstLifeResponseDto = new SpecialMinyeongFirstLifeResponseDto(verificationRecordSpecialMinyeongFirstLife);
            specialMinyeongFirstLifeResponseDtos.add(specialMinyeongFirstLifeResponseDto);
        }

        List<SpecialKookminPublicFirstLifeResponseDto> specialKookminPublicFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminFirstLife verificationRecordSpecialKookminFirstLife : verificationRecordSpecialKookminFirstLifeRepository.findAllByUser(user)) {
            SpecialKookminPublicFirstLifeResponseDto specialKookminPublicFirstLifeResponseDto = new SpecialKookminPublicFirstLifeResponseDto(verificationRecordSpecialKookminFirstLife);
            specialKookminPublicFirstLifeResponseDtos.add(specialKookminPublicFirstLifeResponseDto);
        }

        UserVerificationRecordDto userVerificationRecordDto = UserVerificationRecordDto.builder()
                .generalMinyeongResponseDtos(generalMinyeongResponseDtos)
                .generalKookminResponseDtos(generalKookminResponseDtos)
                .specialMinyeongMultiChildResponseDtos(specialMinyeongMultiChildResponseDtos)
                .specialKookminPublicMultiChildResponseDtos(specialKookminPublicMultiChildResponseDtos)
                .specialMinyeongOldParentResponseDtos(specialMinyeongOldParentResponseDtos)
                .specialKookminPublicOldParentResponseDtos(specialKookminPublicOldParentResponseDtos)
                .specialMinyeongNewlyMarriedResponseDtos(specialMinyeongNewlyMarriedResponseDtos)
                .specialKookminPublicNewlyMarriedResponseDtos(specialKookminPublicNewlyMarriedResponseDtos)
                .specialMinyeongFirstLifeResponseDtos(specialMinyeongFirstLifeResponseDtos)
                .specialKookminPublicFirstLifeResponseDtos(specialKookminPublicFirstLifeResponseDtos)
                .build();

        return userVerificationRecordDto;
    }

    @Override //일반민영조회
    public List<GeneralMinyeongResponseDto> recordGeneralMinyeongResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<GeneralMinyeongResponseDto> generalMinyeongResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong : verificationRecordGeneralMinyeongRepository.findAllByUser(user)) {
            GeneralMinyeongResponseDto generalMinyeongResponseDto = new GeneralMinyeongResponseDto(verificationRecordGeneralMinyeong);
            generalMinyeongResponseDtos.add(generalMinyeongResponseDto);
        }

        return generalMinyeongResponseDtos;
    }

    @Override //일반국민조회
    public List<GeneralKookminResponseDto> recordGeneralKookminResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<GeneralKookminResponseDto> generalKookminResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralKookmin verificationRecordGeneralKookmin : verificationRecordGeneralKookminRepository.findAllByUser(user)) {
            GeneralKookminResponseDto generalKookminResponseDto = new GeneralKookminResponseDto(verificationRecordGeneralKookmin);
            generalKookminResponseDtos.add(generalKookminResponseDto);
        }

        return generalKookminResponseDtos;
    }

    @Override //특별다자녀민영조회
    public List<SpecialMinyeongMultiChildResponseDto> recordSpecialMinyeongMultiChildResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialMinyeongMultiChildResponseDto> specialMinyeongMultiChildResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongMultiChild verificationRecordSpecialMinyeongMultiChild : verificationRecordSpecialMinyeongMultiChildRepository.findAllByUser(user)) {
            SpecialMinyeongMultiChildResponseDto specialMinyeongMultiChildResponseDto = new SpecialMinyeongMultiChildResponseDto(verificationRecordSpecialMinyeongMultiChild);
            specialMinyeongMultiChildResponseDtos.add(specialMinyeongMultiChildResponseDto);
        }

        return specialMinyeongMultiChildResponseDtos;
    }

    @Override //특별다자녀국민조회
    public List<SpecialKookminPublicMultiChildResponseDto> recordSpecialKookminMultiChildResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialKookminPublicMultiChildResponseDto> specialKookminPublicMultiChildResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminMultiChild verificationRecordSpecialKookminMultiChild : verificationRecordSpecialKookminMultiChildRepository.findAllByUser(user)) {
            SpecialKookminPublicMultiChildResponseDto specialKookminPublicMultiChildResponseDto = new SpecialKookminPublicMultiChildResponseDto(verificationRecordSpecialKookminMultiChild);
            specialKookminPublicMultiChildResponseDtos.add(specialKookminPublicMultiChildResponseDto);
        }

        return specialKookminPublicMultiChildResponseDtos;
    }

    @Override //특별노부모민영조회
    public List<SpecialMinyeongOldParentResponseDto> recordSpecialMinyeongOldParentResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialMinyeongOldParentResponseDto> specialMinyeongOldParentResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongOldParent verificationRecordSpecialMinyeongOldParent : verificationRecordSpecialMinyeongOldParentRepository.findAllByUser(user)) {
            SpecialMinyeongOldParentResponseDto specialMinyeongOldParentResponseDto = new SpecialMinyeongOldParentResponseDto(verificationRecordSpecialMinyeongOldParent);
            specialMinyeongOldParentResponseDtos.add(specialMinyeongOldParentResponseDto);
        }

        return specialMinyeongOldParentResponseDtos;
    }

    @Override //특별노부모국민조회
    public List<SpecialKookminPublicOldParentResponseDto> recordSpecialKookminOldParentResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialKookminPublicOldParentResponseDto> specialKookminPublicOldParentResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminOldParent verificationRecordSpecialKookminOldParent : verificationRecordSpecialKookminOldParentRepository.findAllByUser(user)) {
            SpecialKookminPublicOldParentResponseDto specialKookminPublicOldParentResponseDto = new SpecialKookminPublicOldParentResponseDto(verificationRecordSpecialKookminOldParent);
            specialKookminPublicOldParentResponseDtos.add(specialKookminPublicOldParentResponseDto);
        }

        return specialKookminPublicOldParentResponseDtos;
    }

    @Override //특별신혼부부민영조회
    public List<SpecialMinyeongNewlyMarriedResponseDto> recordSpecialMinyeongNewlyMarriedResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialMinyeongNewlyMarriedResponseDto> specialMinyeongNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongNewlyMarried verificationRecordSpecialMinyeongNewlyMarried : verificationRecordSpecialMinyeongNewlyMarriedRepository.findAllByUser(user)) {
            SpecialMinyeongNewlyMarriedResponseDto specialMinyeongNewlyMarriedResponseDto = new SpecialMinyeongNewlyMarriedResponseDto(verificationRecordSpecialMinyeongNewlyMarried);
            specialMinyeongNewlyMarriedResponseDtos.add(specialMinyeongNewlyMarriedResponseDto);
        }

        return specialMinyeongNewlyMarriedResponseDtos;
    }

    @Override //특별신혼부부국민조회
    public List<SpecialKookminPublicNewlyMarriedResponseDto> recordSpecialKookminPublicNewlyMarriedResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialKookminPublicNewlyMarriedResponseDto> specialKookminPublicNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminNewlyMarried verificationRecordSpecialKookminNewlyMarried : verificationRecordSpecialKookminNewlyMarriedRepository.findAllByUser(user)) {
            SpecialKookminPublicNewlyMarriedResponseDto specialKookminPublicNewlyMarriedResponseDto = new SpecialKookminPublicNewlyMarriedResponseDto(verificationRecordSpecialKookminNewlyMarried);
            specialKookminPublicNewlyMarriedResponseDtos.add(specialKookminPublicNewlyMarriedResponseDto);
        }

        return specialKookminPublicNewlyMarriedResponseDtos;
    }

    @Override //특별생애최초민영조회
    public List<SpecialMinyeongFirstLifeResponseDto> recordSpecialMinyeongFirstLifeResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialMinyeongFirstLife verificationRecordSpecialMinyeongFirstLife : verificationRecordSpecialMinyeongFirstLifeRepository.findAllByUser(user)) {
            SpecialMinyeongFirstLifeResponseDto specialMinyeongFirstLifeResponseDto = new SpecialMinyeongFirstLifeResponseDto(verificationRecordSpecialMinyeongFirstLife);
            specialMinyeongFirstLifeResponseDtos.add(specialMinyeongFirstLifeResponseDto);
        }

        return specialMinyeongFirstLifeResponseDtos;
    }

    @Override //특별생애최초국민조회
    public List<SpecialKookminPublicFirstLifeResponseDto> recordSpecialKookminFirstLifeResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<SpecialKookminPublicFirstLifeResponseDto> specialKookminPublicFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationRecordSpecialKookminFirstLife verificationRecordSpecialKookminFirstLife : verificationRecordSpecialKookminFirstLifeRepository.findAllByUser(user)) {
            SpecialKookminPublicFirstLifeResponseDto specialKookminPublicFirstLifeResponseDto = new SpecialKookminPublicFirstLifeResponseDto(verificationRecordSpecialKookminFirstLife);
            specialKookminPublicFirstLifeResponseDtos.add(specialKookminPublicFirstLifeResponseDto);
        }

        return specialKookminPublicFirstLifeResponseDtos;
    }
}
