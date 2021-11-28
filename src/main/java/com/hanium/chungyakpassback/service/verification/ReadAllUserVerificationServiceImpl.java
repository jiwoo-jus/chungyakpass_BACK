package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.ReadAllUserVerificationDto;
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
public class ReadAllUserVerificationServiceImpl implements ReadAllUserVerificationService {

    final UserRepository userRepository;
    final VerificationOfGeneralMinyeongRepository verificationOfGeneralMinyeongRepository;
    final VerificationOfGeneralKookminRepository verificationOfGeneralKookminRepository;
    final VerificationOfSpecialMinyeongMultiChildRepository verificationOfSpecialMinyeongMultiChildRepository;
    final VerificationOfSpecialKookminMultiChildRepository verificationOfSpecialKookminMultiChildRepository;
    final VerificationOfSpecialMinyeongOldParentRepository verificationOfSpecialMinyeongOldParentRepository;
    final VerificationOfSpecialKookminOldParentRepository verificationOfSpecialKookminOldParentRepository;
    final VerificationOfSpecialMinyeongNewlyMarriedRepository verificationOfSpecialMinyeongNewlyMarriedRepository;
    final VerificationOfSpecialKookminNewlyMarriedRepository verificationOfSpecialKookminNewlyMarriedRepository;
    final VerificationOfSpecialMinyeongFirstLifeRepository verificationOfSpecialMinyeongFirstLifeRepository;
    final VerificationOfSpecialKookminFirstLifeRepository verificationOfSpecialKookminFirstLifeRepository;

    @Override
    public ReadAllUserVerificationDto readAllVerifications() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<VerificationOfGeneralMinyeongResponseDto> verificationOfGeneralMinyeongResponseDtos = new ArrayList<>();
        for (VerificationOfGeneralMinyeong verificationOfGeneralMinyeong : verificationOfGeneralMinyeongRepository.findAllByUser(user)) {
            VerificationOfGeneralMinyeongResponseDto verificationOfGeneralMinyeongResponseDto = new VerificationOfGeneralMinyeongResponseDto(verificationOfGeneralMinyeong);
            verificationOfGeneralMinyeongResponseDtos.add(verificationOfGeneralMinyeongResponseDto);
        }

        List<VerificationOfGeneralKookminResponseDto> verificationOfGeneralKookminResponseDtos = new ArrayList<>();
        for (VerificationOfGeneralKookmin verificationOfGeneralKookmin : verificationOfGeneralKookminRepository.findAllByUser(user)) {
            VerificationOfGeneralKookminResponseDto verificationOfGeneralKookminResponseDto = new VerificationOfGeneralKookminResponseDto(verificationOfGeneralKookmin);
            verificationOfGeneralKookminResponseDtos.add(verificationOfGeneralKookminResponseDto);
        }

        List<VerificationOfSpecialMinyeongMultiChildResponseDto> verificationOfSpecialMinyeongMultiChildResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialMinyeongMultiChild verificationOfSpecialMinyeongMultiChild : verificationOfSpecialMinyeongMultiChildRepository.findAllByUser(user)) {
            VerificationOfSpecialMinyeongMultiChildResponseDto verificationOfSpecialMinyeongMultiChildResponseDto = new VerificationOfSpecialMinyeongMultiChildResponseDto(verificationOfSpecialMinyeongMultiChild);
            verificationOfSpecialMinyeongMultiChildResponseDtos.add(verificationOfSpecialMinyeongMultiChildResponseDto);
        }

        List<VerificationOfSpecialKookminPublicMultiChildResponseDto> verificationOfSpecialKookminPublicMultiChildResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialKookminMultiChild verificationOfSpecialKookminMultiChild : verificationOfSpecialKookminMultiChildRepository.findAllByUser(user)) {
            VerificationOfSpecialKookminPublicMultiChildResponseDto verificationOfSpecialKookminPublicMultiChildResponseDto = new VerificationOfSpecialKookminPublicMultiChildResponseDto(verificationOfSpecialKookminMultiChild);
            verificationOfSpecialKookminPublicMultiChildResponseDtos.add(verificationOfSpecialKookminPublicMultiChildResponseDto);
        }

        List<VerificationOfSpecialMinyeongOldParentResponseDto> verificationOfSpecialMinyeongOldParentResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialMinyeongOldParent verificationOfSpecialMinyeongOldParent : verificationOfSpecialMinyeongOldParentRepository.findAllByUser(user)) {
            VerificationOfSpecialMinyeongOldParentResponseDto verificationOfSpecialMinyeongOldParentResponseDto = new VerificationOfSpecialMinyeongOldParentResponseDto(verificationOfSpecialMinyeongOldParent);
            verificationOfSpecialMinyeongOldParentResponseDtos.add(verificationOfSpecialMinyeongOldParentResponseDto);
        }

        List<VerificationOfSpecialKookminPublicOldParentResponseDto> verificationOfSpecialKookminPublicOldParentResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialKookminOldParent verificationOfSpecialKookminOldParent : verificationOfSpecialKookminOldParentRepository.findAllByUser(user)) {
            VerificationOfSpecialKookminPublicOldParentResponseDto verificationOfSpecialKookminPublicOldParentResponseDto = new VerificationOfSpecialKookminPublicOldParentResponseDto(verificationOfSpecialKookminOldParent);
            verificationOfSpecialKookminPublicOldParentResponseDtos.add(verificationOfSpecialKookminPublicOldParentResponseDto);
        }

        List<VerificationOfSpecialMinyeongNewlyMarriedResponseDto> verificationOfSpecialMinyeongNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialMinyeongNewlyMarried verificationOfSpecialMinyeongNewlyMarried : verificationOfSpecialMinyeongNewlyMarriedRepository.findAllByUser(user)) {
            VerificationOfSpecialMinyeongNewlyMarriedResponseDto verificationOfSpecialMinyeongNewlyMarriedResponseDto = new VerificationOfSpecialMinyeongNewlyMarriedResponseDto(verificationOfSpecialMinyeongNewlyMarried);
            verificationOfSpecialMinyeongNewlyMarriedResponseDtos.add(verificationOfSpecialMinyeongNewlyMarriedResponseDto);
        }

        List<VerificationOfSpecialKookminPublicNewlyMarriedResponseDto> verificationOfSpecialKookminPublicNewlyMarriedResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialKookminNewlyMarried verificationOfSpecialKookminNewlyMarried : verificationOfSpecialKookminNewlyMarriedRepository.findAllByUser(user)) {
            VerificationOfSpecialKookminPublicNewlyMarriedResponseDto verificationOfSpecialKookminPublicNewlyMarriedResponseDto = new VerificationOfSpecialKookminPublicNewlyMarriedResponseDto(verificationOfSpecialKookminNewlyMarried);
            verificationOfSpecialKookminPublicNewlyMarriedResponseDtos.add(verificationOfSpecialKookminPublicNewlyMarriedResponseDto);
        }

        List<VerificationOfSpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialMinyeongFirstLife verificationOfSpecialMinyeongFirstLife : verificationOfSpecialMinyeongFirstLifeRepository.findAllByUser(user)) {
            VerificationOfSpecialMinyeongFirstLifeResponseDto specialMinyeongFirstLifeResponseDto = new VerificationOfSpecialMinyeongFirstLifeResponseDto(verificationOfSpecialMinyeongFirstLife);
            specialMinyeongFirstLifeResponseDtos.add(specialMinyeongFirstLifeResponseDto);
        }

        List<VerificationOfSpecialKookminPublicFirstLifeResponseDto> verificationOfSpecialKookminPublicFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialKookminFirstLife verificationOfSpecialKookminFirstLife : verificationOfSpecialKookminFirstLifeRepository.findAllByUser(user)) {
            VerificationOfSpecialKookminPublicFirstLifeResponseDto verificationOfSpecialKookminPublicFirstLifeResponseDto = new VerificationOfSpecialKookminPublicFirstLifeResponseDto(verificationOfSpecialKookminFirstLife);
            verificationOfSpecialKookminPublicFirstLifeResponseDtos.add(verificationOfSpecialKookminPublicFirstLifeResponseDto);
        }

        ReadAllUserVerificationDto readAllUserVerificationDto = ReadAllUserVerificationDto.builder()
                .verificationOfGeneralMinyeongResponseDtos(verificationOfGeneralMinyeongResponseDtos)
                .verificationOfGeneralKookminResponseDtos(verificationOfGeneralKookminResponseDtos)
                .verificationOfSpecialMinyeongMultiChildResponseDtos(verificationOfSpecialMinyeongMultiChildResponseDtos)
                .verificationOfSpecialKookminPublicMultiChildResponseDtos(verificationOfSpecialKookminPublicMultiChildResponseDtos)
                .verificationOfSpecialMinyeongOldParentResponseDtos(verificationOfSpecialMinyeongOldParentResponseDtos)
                .verificationOfSpecialKookminPublicOldParentResponseDtos(verificationOfSpecialKookminPublicOldParentResponseDtos)
                .verificationOfSpecialMinyeongNewlyMarriedResponseDtos(verificationOfSpecialMinyeongNewlyMarriedResponseDtos)
                .verificationOfSpecialKookminPublicNewlyMarriedResponseDtos(verificationOfSpecialKookminPublicNewlyMarriedResponseDtos)
                .verificationOfSpecialMinyeongFirstLifeResponseDtos(specialMinyeongFirstLifeResponseDtos)
                .verificationOfSpecialKookminPublicFirstLifeResponseDtos(verificationOfSpecialKookminPublicFirstLifeResponseDtos)
                .build();

        return readAllUserVerificationDto;
    }

}
