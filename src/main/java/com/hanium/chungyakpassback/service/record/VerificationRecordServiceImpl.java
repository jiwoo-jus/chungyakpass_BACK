//package com.hanium.chungyakpassback.service.record;
//
//import com.hanium.chungyakpassback.dto.record.*;
//import com.hanium.chungyakpassback.entity.input.User;
//import com.hanium.chungyakpassback.entity.record.VerificationRecord;
//import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookminRequest;
//import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralResponse;
//import com.hanium.chungyakpassback.enumtype.ErrorCode;
//import com.hanium.chungyakpassback.enumtype.VerificationResult;
//import com.hanium.chungyakpassback.handler.CustomException;
//import com.hanium.chungyakpassback.repository.input.UserRepository;
//import com.hanium.chungyakpassback.repository.record.VerificationRecordGeneralKookminRequestRepository;
//import com.hanium.chungyakpassback.repository.record.VerificationRecordGeneralResponseRepository;
//import com.hanium.chungyakpassback.repository.record.VerificationRecordRepository;
//import com.hanium.chungyakpassback.util.SecurityUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class VerificationRecordServiceImpl implements VerificationRecordService {
//
//    private final VerificationRecordRepository verificationRecordRepository;
//    private final VerificationRecordGeneralResponseRepository verificationRecordGeneralResponseRepository;
//    private final UserRepository userRepository;
//    private final VerificationRecordGeneralKookminRequestRepository verificationRecordGeneralKookminRequestRepository;
//
//
////    @Override
////    @Transactional(rollbackFor = Exception.class)
////    public VerificationRecordResponseDto verificationRecord(VerificationRecordDto verificationRecordDto) {
////        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
////
////        return new VerificationRecordResponseDto(verificationRecordRepository.save(verificationRecordDto.toEntity(user)));
////    }
//
////    @Override
////    @Transactional(rollbackFor = Exception.class)
////    public VerificationRecordGeneralResponseDto verificationRecordGeneralResponse(VerificationRecordGeneralDto verificationRecordGeneralDto) {
////        VerificationRecord verificationRecord = verificationRecordRepository.findById(verificationRecordGeneralDto.getVerificationRecordId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFICATION_RECORD));
////        VerificationRecordGeneralResponse verificationRecordGeneralResponse = verificationRecordGeneralDto.toEntity(verificationRecord);
////
////        verificationRecord.setVerificationResult(VerificationResult.청약자격결과_일반);
////
////        verificationRecordGeneralResponseRepository.save(verificationRecordGeneralResponse);
////        return new VerificationRecordGeneralResponseDto(verificationRecordGeneralResponse);
////    }
////
////    @Override
////    @Transactional(rollbackFor = Exception.class)
////    public VerificationRecordGeneralKookminResponseDto verificationRecordGeneralKookminRequest(VerificationRecordGeneralKookminDto verificationRecordGeneralKookminDto) {
////        VerificationRecord verificationRecord = verificationRecordRepository.findById(verificationRecordGeneralKookminDto.getVerificationRecordId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFICATION_RECORD));
////        VerificationRecordGeneralKookminRequest verificationRecordGeneralKookminRequest = verificationRecordGeneralKookminDto.toEntity(verificationRecord);
////
////        verificationRecord.setVerificationResult(VerificationResult.청약자격결과_일반국민);
////
////        verificationRecordGeneralKookminRequestRepository.save(verificationRecordGeneralKookminRequest);
////        return new VerificationRecordGeneralKookminResponseDto(verificationRecordGeneralKookminRequest);
////    }
//
//}
