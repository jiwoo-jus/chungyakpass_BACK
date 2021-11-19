package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.dto.verification.GeneralMinyeongResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserVerificationRecordDto {
    List<GeneralMinyeongResponseDto> generalMinyeongResponseDtos;
}
