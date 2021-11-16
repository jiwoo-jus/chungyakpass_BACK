package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecord;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class VerificationRecordDto {

//    public VerificationResult verificationResult; //청약자격결과종류

    public VerificationRecord toEntity(User user) {
        return VerificationRecord.builder()
                .user(user)
                .build();
    }

}
