package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.record.*;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookminRequest;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.record.VerificationRecordGeneralKookminRequestRepository;
import com.hanium.chungyakpassback.service.record.VerificationRecordService;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/record")
public class VerificationRecordController {

    private final UserRepository userRepository;
    private final VerificationRecordGeneralKookminRequestRepository verificationRecordGeneralKookminRequestRepository;

    public VerificationRecordController(UserRepository userRepository, VerificationRecordGeneralKookminRequestRepository verificationRecordGeneralKookminRequestRepository) {
        this.userRepository = userRepository;
        this.verificationRecordGeneralKookminRequestRepository = verificationRecordGeneralKookminRequestRepository;
    }

//    @PostMapping("/result")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<VerificationRecordResponseDto> verificatonRecord(@RequestBody VerificationRecordDto verificationRecordDto) {
//
//        return ResponseEntity.ok(verificationRecordService.verificationRecord(verificationRecordDto));
//    }

    @PostMapping("/general")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordGeneralDto> verificationRecordGeneralResponse(@RequestBody VerificationRecordGeneralDto verificationRecordGeneralDto) {
        VerificationRecordGeneralKookminRequest verificationRecordGeneralKookminRequest = verificationRecordGeneralKookminRequestRepository.findById(verificationRecordGeneralDto.getVerificationRecordGeneralKookminRequestId()).get();
        verificationRecordGeneralKookminRequest.setRanking(verificationRecordGeneralDto.getRanking());
        verificationRecordGeneralKookminRequest.setSibilingSupportYn(verificationRecordGeneralDto.getSibilingSupportYn());
        verificationRecordGeneralKookminRequest.setTwentiesSoleHouseHolderYn(verificationRecordGeneralDto.getTwentiesSoleHouseHolderYn());
        verificationRecordGeneralKookminRequestRepository.save(verificationRecordGeneralKookminRequest);

        return ResponseEntity.ok(verificationRecordGeneralDto);
    }

//    @PostMapping("/general/kookmin")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<VerificationRecordGeneralKookminResponseDto> verificationRecordGeneralKookminRequest(@RequestBody VerificationRecordGeneralKookminDto verificationRecordGeneralKookminDto) {
//
//        return ResponseEntity.ok(verificationRecordService.verificationRecordGeneralKookminRequest(verificationRecordGeneralKookminDto));
//    }

}
