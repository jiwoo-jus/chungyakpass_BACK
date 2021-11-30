package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReadAllUserVerificationDto {

    // 모든 자격 유형들을 한 번에 담는 dto
    List<VerificationOfGeneralMinyeongResponseDto> verificationOfGeneralMinyeongResponseDtos;
    List<VerificationOfGeneralKookminResponseDto> verificationOfGeneralKookminResponseDtos;
    List<VerificationOfSpecialMinyeongMultiChildResponseDto> verificationOfSpecialMinyeongMultiChildResponseDtos;
    List<VerificationOfSpecialKookminPublicMultiChildResponseDto> verificationOfSpecialKookminPublicMultiChildResponseDtos;
    List<VerificationOfSpecialMinyeongOldParentResponseDto> verificationOfSpecialMinyeongOldParentResponseDtos;
    List<VerificationOfSpecialKookminPublicOldParentResponseDto> verificationOfSpecialKookminPublicOldParentResponseDtos;
    List<VerificationOfSpecialMinyeongNewlyMarriedResponseDto> verificationOfSpecialMinyeongNewlyMarriedResponseDtos;
    List<VerificationOfSpecialKookminPublicNewlyMarriedResponseDto> verificationOfSpecialKookminPublicNewlyMarriedResponseDtos;
    List<VerificationOfSpecialMinyeongFirstLifeResponseDto> verificationOfSpecialMinyeongFirstLifeResponseDtos;
    List<VerificationOfSpecialKookminPublicFirstLifeResponseDto> verificationOfSpecialKookminPublicFirstLifeResponseDtos;

}
