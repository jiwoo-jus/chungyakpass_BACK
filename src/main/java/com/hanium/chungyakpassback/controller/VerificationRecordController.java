package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.record.*;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.record.VerificationRecordService;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/record")
public class VerificationRecordController {

    private final VerificationRecordService verificationRecordService;
    private final UserRepository userRepository;

    public VerificationRecordController(VerificationRecordService verificationRecordService, UserRepository userRepository) {
        this.verificationRecordService = verificationRecordService;
        this.userRepository = userRepository;
    }

    @PostMapping("/result")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordResponseDto> verificatonRecord(@RequestBody VerificationRecordDto verificationRecordDto) {

        return ResponseEntity.ok(verificationRecordService.verificationRecord(verificationRecordDto));
    }

    @PostMapping("/general")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordGeneralResponseDto> verificationRecordGeneralResponse(@RequestBody VerificationRecordGeneralDto verificationRecordGeneralDto) {

        return ResponseEntity.ok(verificationRecordService.verificationRecordGeneralResponse(verificationRecordGeneralDto));
    }

    @PostMapping("/general/kookmin")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordGeneralKookminResponseDto> verificationRecordGeneralKookminRequest(@RequestBody VerificationRecordGeneralKookminDto verificationRecordGeneralKookminDto) {

        return ResponseEntity.ok(verificationRecordService.verificationRecordGeneralKookminRequest(verificationRecordGeneralKookminDto));
    }

}
