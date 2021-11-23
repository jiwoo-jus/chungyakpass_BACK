package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReadVerificationDto {

    List<VerificationOfGeneralMinyeongResponseDto> verificationOfGeneralMinyeongResponseDtos;
    List<VerificationOfGeneralKookminResponseDto> verificationOfGeneralKookminResponseDtos;
    List<VerificationOfSpecialMinyeongMultiChildResponseDto> verificationOfSpecialMinyeongMultiChildResponseDtos;
    List<VerificationOfSpecialKookminPublicMultiChildResponseDto> verificationOfSpecialKookminPublicMultiChildResponseDtos;
    List<VerificationOfSpecialMinyeongOldParentResponseDto> verificationOfSpecialMinyeongOldParentResponseDtos;
    List<VerificationOfSpecialKookminPublicOldParentResponseDto> verificationOfSpecialKookminPublicOldParentResponseDtos;
    List<VerificationOfSpecialMinyeongNewlyMarriedResponseDto> verificationOfSpecialMinyeongNewlyMarriedResponseDtos;
    //    List<SpecialKookminNewlyMarriedResponseDto> specialKookminNewlyMarriedResponseDtos;
    List<VerificationOfSpecialKookminPublicNewlyMarriedResponseDto> verificationOfSpecialKookminPublicNewlyMarriedResponseDtos;
    List<VerificationOfSpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos;
    List<VerificationOfSpecialKookminPublicFirstLifeResponseDto> verificationOfSpecialKookminPublicFirstLifeResponseDtos;

}
