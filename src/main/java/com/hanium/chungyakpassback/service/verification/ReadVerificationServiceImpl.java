package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.ReadVerificationDto;
import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.*;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.verification.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReadVerificationServiceImpl implements ReadVerificationService {

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
    public ReadVerificationDto readAllVerifications() {
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

        ReadVerificationDto readVerificationDto = ReadVerificationDto.builder()
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

        return readVerificationDto;
    }

}
