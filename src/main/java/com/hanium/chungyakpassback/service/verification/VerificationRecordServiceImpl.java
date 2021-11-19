package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.point.GeneralMinyeongResponsePointDto;
import com.hanium.chungyakpassback.dto.verification.GeneralMinyeongResponseDto;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralMinyeong;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.record.VerificationRecordGeneralMinyeongRepository;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VerificationRecordServiceImpl implements VerificationRecordService {

    final UserRepository userRepository;
    final VerificationRecordGeneralMinyeongRepository verificationRecordGeneralMinyeongRepository;


    @Override
    public List<GeneralMinyeongResponseDto> recordGeneralMinyeongResponseVerification() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<GeneralMinyeongResponseDto> generalMinyeongResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong : verificationRecordGeneralMinyeongRepository.findAllByUser(user)) {
            GeneralMinyeongResponseDto generalMinyeongResponseDto = new GeneralMinyeongResponseDto(verificationRecordGeneralMinyeong);
            generalMinyeongResponseDtos.add(generalMinyeongResponseDto);
        }

        return generalMinyeongResponseDtos;
    }
}
