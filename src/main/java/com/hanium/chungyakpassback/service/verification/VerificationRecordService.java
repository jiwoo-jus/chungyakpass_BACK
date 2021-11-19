package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.GeneralMinyeongResponseDto;

import java.util.List;

public interface VerificationRecordService {
    List<GeneralMinyeongResponseDto> recordGeneralMinyeongResponseVerification();
}
