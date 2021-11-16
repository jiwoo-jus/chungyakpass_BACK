package com.hanium.chungyakpassback.service.record;

import com.hanium.chungyakpassback.dto.record.*;

public interface VerificationRecordService {

    VerificationRecordResponseDto verificationRecord(VerificationRecordDto verificationRecordDto);

    VerificationRecordGeneralResponseDto verificationRecordGeneralResponse(VerificationRecordGeneralDto verificationRecordGeneralDto);

    VerificationRecordGeneralKookminResponseDto verificationRecordGeneralKookminRequest(VerificationRecordGeneralKookminDto verificationRecordGeneralKookminDto);
}
