package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.entity.record.VerificationRecord;
import com.hanium.chungyakpassback.enumtype.VerificationResult;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationRecordResponseDto {

    public Long id;

    public VerificationResult verificationResult; //청약자격결과종류

    @Builder
    public VerificationRecordResponseDto(VerificationRecord verificationRecord) {
        this.id = verificationRecord.getId();
        this.verificationResult = verificationRecord.getVerificationResult();
    }
}
