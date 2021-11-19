package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.record.UserVerificationRecordDto;
import com.hanium.chungyakpassback.dto.verification.*;

import java.util.List;

public interface VerificationRecordService {

    UserVerificationRecordDto readAllUserVerificationRecord();

    List<GeneralMinyeongResponseDto> recordGeneralMinyeongResponseVerification();

    List<GeneralKookminResponseDto> recordGeneralKookminResponseVerification();

    List<SpecialMinyeongMultiChildResponseDto> recordSpecialMinyeongMultiChildResponseVerification();

    List<SpecialKookminPublicMultiChildResponseDto> recordSpecialKookminMultiChildResponseVerification();

    List<SpecialMinyeongOldParentResponseDto> recordSpecialMinyeongOldParentResponseVerification();

    List<SpecialKookminPublicOldParentResponseDto> recordSpecialKookminOldParentResponseVerification();

    List<SpecialMinyeongNewlyMarriedResponseDto> recordSpecialMinyeongNewlyMarriedResponseVerification();

//    List<SpecialKookminNewlyMarriedResponseDto> recordSpecialKookminNewlyMarriedResponseVerification();

    List<SpecialKookminPublicNewlyMarriedResponseDto> recordSpecialKookminPublicNewlyMarriedResponseVerification();

    List<SpecialMinyeongFirstLifeResponseDto> recordSpecialMinyeongFirstLifeResponseVerification();

    List<SpecialKookminPublicFirstLifeResponseDto> recordSpecialKookminFirstLifeResponseVerification();

}
