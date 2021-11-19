package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.dto.verification.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserVerificationRecordDto {

    List<GeneralMinyeongResponseDto> generalMinyeongResponseDtos;
    List<GeneralKookminResponseDto> generalKookminResponseDtos;
    List<SpecialMinyeongMultiChildResponseDto> specialMinyeongMultiChildResponseDtos;
    List<SpecialKookminPublicMultiChildResponseDto> specialKookminPublicMultiChildResponseDtos;
    List<SpecialMinyeongOldParentResponseDto> specialMinyeongOldParentResponseDtos;
    List<SpecialKookminPublicOldParentResponseDto> specialKookminPublicOldParentResponseDtos;
    List<SpecialMinyeongNewlyMarriedResponseDto> specialMinyeongNewlyMarriedResponseDtos;
    //    List<SpecialKookminNewlyMarriedResponseDto> specialKookminNewlyMarriedResponseDtos;
    List<SpecialKookminPublicNewlyMarriedResponseDto> specialKookminPublicNewlyMarriedResponseDtos;
    List<SpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos;
    List<SpecialKookminPublicFirstLifeResponseDto> specialKookminPublicFirstLifeResponseDtos;

}
