package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.record.*;
import com.hanium.chungyakpassback.dto.verification.VerificationRecordSpecialKookminFirstLifeDto;
import com.hanium.chungyakpassback.entity.record.*;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.record.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/record")
public class VerificationRecordController {

    private final UserRepository userRepository;
    private final VerificationRecordGeneralKookminRepository verificationRecordGeneralKookminRepository;
    private final VerificationRecordGeneralMinyeongRepository verificationRecordGeneralMinyeongRepository;
    private final VerificationRecordSpecialMinyeongMultiChildRepository verificationRecordSpecialMinyeongMultiChildRepository;
    private final VerificationRecordSpecialKookminMultiChildRepository verificationRecordSpecialKookminMultiChildRepository;
    private final VerificationRecordSpecialMinyeongOldParentRepository verificationRecordSpecialMinyeongOldParentRepository;
    private final VerificationRecordSpecialKookminOldParentRepository verificationRecordSpecialKookminOldParentRepository;
    private final VerificationRecordSpecialMinyeongNewlyMarriedRepository verificationRecordSpecialMinyeongNewlyMarriedRepository;
    private final VerificationRecordSpecialKookminNewlyMarriedRepository verificationRecordSpecialKookminNewlyMarriedRepository;
    private final VerificationRecordSpecialMinyeongFirstLifeRepository verificationRecordSpecialMinyeongFirstLifeRepository;
    private final VerificationRecordSpecialKookminFirstLifeRepository verificationRecordSpecialKookminFirstLifeRepository;

    public VerificationRecordController(UserRepository userRepository, VerificationRecordGeneralKookminRepository verificationRecordGeneralKookminRepository, VerificationRecordGeneralMinyeongRepository verificationRecordGeneralMinyeongRepository, VerificationRecordSpecialMinyeongMultiChildRepository verificationRecordSpecialMinyeongMultiChildRepository, VerificationRecordSpecialKookminMultiChildRepository verificationRecordSpecialKookminMultiChildRepository, VerificationRecordSpecialMinyeongOldParentRepository verificationRecordSpecialMinyeongOldParentRepository, VerificationRecordSpecialKookminOldParentRepository verificationRecordSpecialKookminOldParentRepository, VerificationRecordSpecialMinyeongNewlyMarriedRepository verificationRecordSpecialMinyeongNewlyMarriedRepository, VerificationRecordSpecialKookminNewlyMarriedRepository verificationRecordSpecialKookminNewlyMarriedRepository, VerificationRecordSpecialMinyeongFirstLifeRepository verificationRecordSpecialMinyeongFirstLifeRepository, VerificationRecordSpecialKookminFirstLifeRepository verificationRecordSpecialKookminFirstLifeRepository) {
        this.userRepository = userRepository;
        this.verificationRecordGeneralKookminRepository = verificationRecordGeneralKookminRepository;
        this.verificationRecordGeneralMinyeongRepository = verificationRecordGeneralMinyeongRepository;
        this.verificationRecordSpecialMinyeongMultiChildRepository = verificationRecordSpecialMinyeongMultiChildRepository;
        this.verificationRecordSpecialKookminMultiChildRepository = verificationRecordSpecialKookminMultiChildRepository;
        this.verificationRecordSpecialMinyeongOldParentRepository = verificationRecordSpecialMinyeongOldParentRepository;
        this.verificationRecordSpecialKookminOldParentRepository = verificationRecordSpecialKookminOldParentRepository;
        this.verificationRecordSpecialMinyeongNewlyMarriedRepository = verificationRecordSpecialMinyeongNewlyMarriedRepository;
        this.verificationRecordSpecialKookminNewlyMarriedRepository = verificationRecordSpecialKookminNewlyMarriedRepository;
        this.verificationRecordSpecialMinyeongFirstLifeRepository = verificationRecordSpecialMinyeongFirstLifeRepository;
        this.verificationRecordSpecialKookminFirstLifeRepository = verificationRecordSpecialKookminFirstLifeRepository;
    }

    @PostMapping("/general/minyeong") //일반민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordGeneralMinyeongDto> verificationRecordGeneralResponse(@RequestBody VerificationRecordGeneralMinyeongDto verificationRecordGeneralMinyeongDto) {
        VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong = verificationRecordGeneralMinyeongRepository.findById(verificationRecordGeneralMinyeongDto.getVerificationRecordGeneralMinyeongId()).get();
        verificationRecordGeneralMinyeong.setRanking(verificationRecordGeneralMinyeongDto.getRanking());
        verificationRecordGeneralMinyeong.setSibilingSupportYn(verificationRecordGeneralMinyeongDto.getSibilingSupportYn());
        verificationRecordGeneralMinyeongRepository.save(verificationRecordGeneralMinyeong);

        return ResponseEntity.ok(verificationRecordGeneralMinyeongDto);
    }

    @PostMapping("/general/kookmin") //일반국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordGeneralKookminDto> verificationRecordGeneralResponse(@RequestBody VerificationRecordGeneralKookminDto verificationRecordGeneralKookminDto) {
        VerificationRecordGeneralKookmin verificationRecordGeneralKookmin = verificationRecordGeneralKookminRepository.findById(verificationRecordGeneralKookminDto.getVerificationRecordGeneralKookminId()).get();
        verificationRecordGeneralKookmin.setRanking(verificationRecordGeneralKookminDto.getRanking());
        verificationRecordGeneralKookmin.setSibilingSupportYn(verificationRecordGeneralKookminDto.getSibilingSupportYn());
        verificationRecordGeneralKookmin.setTwentiesSoleHouseHolderYn(verificationRecordGeneralKookminDto.getTwentiesSoleHouseHolderYn());
        verificationRecordGeneralKookminRepository.save(verificationRecordGeneralKookmin);

        return ResponseEntity.ok(verificationRecordGeneralKookminDto);
    }

    @PostMapping("/special/minyeong/multichild") //특별다자녀민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialMinyeongMultiChildDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialMinyeongMultiChildDto verificationRecordSpecialMinyeongMultiChildDto) {
        VerificationRecordSpecialMinyeongMultiChild verificationRecordSpecialMinyeongMultiChild = verificationRecordSpecialMinyeongMultiChildRepository.findById(verificationRecordSpecialMinyeongMultiChildDto.getVerificationRecordSpecialMinyeongMultiChildId()).get();
        verificationRecordSpecialMinyeongMultiChild.setRanking(verificationRecordSpecialMinyeongMultiChildDto.getRanking());
        verificationRecordSpecialMinyeongMultiChild.setSibilingSupportYn(verificationRecordSpecialMinyeongMultiChildDto.getSibilingSupportYn());
        verificationRecordSpecialMinyeongMultiChildRepository.save(verificationRecordSpecialMinyeongMultiChild);

        return ResponseEntity.ok(verificationRecordSpecialMinyeongMultiChildDto);
    }

    @PostMapping("/special/kookmin/multichild") //특별다자녀국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialKookminMultiChildDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialKookminMultiChildDto verificationRecordSpecialKookminMultiChildDto) {
        VerificationRecordSpecialKookminMultiChild verificationRecordSpecialKookminMultiChild = verificationRecordSpecialKookminMultiChildRepository.findById(verificationRecordSpecialKookminMultiChildDto.getVerificationRecordSpecialKookminMultiChildId()).get();
        verificationRecordSpecialKookminMultiChild.setRanking(verificationRecordSpecialKookminMultiChildDto.getRanking());
        verificationRecordSpecialKookminMultiChild.setKookminType(verificationRecordSpecialKookminMultiChildDto.getKookminType());
        verificationRecordSpecialKookminMultiChild.setSibilingSupportYn(verificationRecordSpecialKookminMultiChildDto.getSibilingSupportYn());
        verificationRecordSpecialKookminMultiChildRepository.save(verificationRecordSpecialKookminMultiChild);

        return ResponseEntity.ok(verificationRecordSpecialKookminMultiChildDto);
    }

    @PostMapping("/special/minyeong/oldparent") //특별노부모민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialMinyeongOldParentDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialMinyeongOldParentDto verificationRecordSpecialMinyeongOldParentDto) {
        VerificationRecordSpecialMinyeongOldParent verificationRecordSpecialMinyeongOldParent = verificationRecordSpecialMinyeongOldParentRepository.findById(verificationRecordSpecialMinyeongOldParentDto.getVerificationRecordSpecialMinyeongOldParentRequestId()).get();
        verificationRecordSpecialMinyeongOldParent.setRanking(verificationRecordSpecialMinyeongOldParentDto.getRanking());
        verificationRecordSpecialMinyeongOldParent.setSibilingSupportYn(verificationRecordSpecialMinyeongOldParentDto.getSibilingSupportYn());
        verificationRecordSpecialMinyeongOldParentRepository.save(verificationRecordSpecialMinyeongOldParent);

        return ResponseEntity.ok(verificationRecordSpecialMinyeongOldParentDto);
    }

    @PostMapping("/special/kookmin/oldparent") //특별노부모국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialKookminOldParentDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialKookminOldParentDto verificationRecordSpecialKookminOldParentDto) {
        VerificationRecordSpecialKookminOldParent verificationRecordSpecialKookminOldParent = verificationRecordSpecialKookminOldParentRepository.findById(verificationRecordSpecialKookminOldParentDto.getVerificationRecordSpecialKookminOldParentId()).get();
        verificationRecordSpecialKookminOldParent.setRanking(verificationRecordSpecialKookminOldParentDto.getRanking());
        verificationRecordSpecialKookminOldParent.setKookminType(verificationRecordSpecialKookminOldParentDto.getKookminType());
        verificationRecordSpecialKookminOldParent.setSibilingSupportYn(verificationRecordSpecialKookminOldParentDto.getSibilingSupportYn());
        verificationRecordSpecialKookminOldParentRepository.save(verificationRecordSpecialKookminOldParent);

        return ResponseEntity.ok(verificationRecordSpecialKookminOldParentDto);
    }

    @PostMapping("/special/minyeong/newlymarried") //특별신혼부부민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialMinyeongNewlyMarriedDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialMinyeongNewlyMarriedDto verificationRecordSpecialMinyeongNewlyMarriedDto) {
        VerificationRecordSpecialMinyeongNewlyMarried verificationRecordSpecialMinyeongNewlyMarried = verificationRecordSpecialMinyeongNewlyMarriedRepository.findById(verificationRecordSpecialMinyeongNewlyMarriedDto.getVerificationRecordSpecialMinyeongNewlyMarriedId()).get();
        verificationRecordSpecialMinyeongNewlyMarried.setRanking(verificationRecordSpecialMinyeongNewlyMarriedDto.getRanking());
        verificationRecordSpecialMinyeongNewlyMarried.setSibilingSupportYn(verificationRecordSpecialMinyeongNewlyMarriedDto.getSibilingSupportYn());
        verificationRecordSpecialMinyeongNewlyMarriedRepository.save(verificationRecordSpecialMinyeongNewlyMarried);

        return ResponseEntity.ok(verificationRecordSpecialMinyeongNewlyMarriedDto);
    }

    @PostMapping("/special/kookmin/newlymarried") //특별신혼부부국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialKookminNewlyMarriedDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialKookminNewlyMarriedDto verificationRecordSpecialKookminNewlyMarriedDto) {
        VerificationRecordSpecialKookminNewlyMarried verificationRecordSpecialKookminNewlyMarried = verificationRecordSpecialKookminNewlyMarriedRepository.findById(verificationRecordSpecialKookminNewlyMarriedDto.getVerificationRecordSpecialKookminNewlyMarriedId()).get();
        verificationRecordSpecialKookminNewlyMarried.setRanking(verificationRecordSpecialKookminNewlyMarriedDto.getRanking());
        verificationRecordSpecialKookminNewlyMarried.setKookminType(verificationRecordSpecialKookminNewlyMarriedDto.getKookminType());
        verificationRecordSpecialKookminNewlyMarried.setPreNewMarriedYn(verificationRecordSpecialKookminNewlyMarriedDto.getPreNewMarriedYn());
        verificationRecordSpecialKookminNewlyMarried.setSibilingSupportYn(verificationRecordSpecialKookminNewlyMarriedDto.getSibilingSupportYn());
        verificationRecordSpecialKookminNewlyMarriedRepository.save(verificationRecordSpecialKookminNewlyMarried);

        return ResponseEntity.ok(verificationRecordSpecialKookminNewlyMarriedDto);
    }

    @PostMapping("/special/minyeong/firstlife") //특별생애최초민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialMinyeongFirstLifeDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialMinyeongFirstLifeDto verificationRecordSpecialMinyeongFirstLifeDto) {
        VerificationRecordSpecialMinyeongFirstLife verificationRecordSpecialMinyeongFirstLife = verificationRecordSpecialMinyeongFirstLifeRepository.findById(verificationRecordSpecialMinyeongFirstLifeDto.getVerificationRecordSpecialMinyeongFirstLifeId()).get();
        verificationRecordSpecialMinyeongFirstLife.setRanking(verificationRecordSpecialMinyeongFirstLifeDto.getRanking());
        verificationRecordSpecialMinyeongFirstLife.setTaxOver5yearsYn(verificationRecordSpecialMinyeongFirstLifeDto.getTaxOver5yearsYn());
        verificationRecordSpecialMinyeongFirstLife.setFirstRankHistoryYn(verificationRecordSpecialMinyeongFirstLifeDto.getFirstRankHistoryYn());
        verificationRecordSpecialMinyeongFirstLife.setSibilingSupportYn(verificationRecordSpecialMinyeongFirstLifeDto.getSibilingSupportYn());
        verificationRecordSpecialMinyeongFirstLifeRepository.save(verificationRecordSpecialMinyeongFirstLife);

        return ResponseEntity.ok(verificationRecordSpecialMinyeongFirstLifeDto);
    }

    @PostMapping("/special/kookmin/firstlife") //특별생애최초국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<VerificationRecordSpecialKookminFirstLifeDto> verificationRecordSpecialResponse(@RequestBody VerificationRecordSpecialKookminFirstLifeDto verificationRecordSpecialKookminFirstLifeDto) {
        VerificationRecordSpecialKookminFirstLife verificationRecordSpecialKookminFirstLife = verificationRecordSpecialKookminFirstLifeRepository.findById(verificationRecordSpecialKookminFirstLifeDto.getVerificationRecordSpecialKookminFirstLifeId()).get();
        verificationRecordSpecialKookminFirstLife.setRanking(verificationRecordSpecialKookminFirstLifeDto.getRanking());
        verificationRecordSpecialKookminFirstLife.setTaxOver5yearsYn(verificationRecordSpecialKookminFirstLifeDto.getTaxOver5yearsYn());
        verificationRecordSpecialKookminFirstLife.setKookminType((verificationRecordSpecialKookminFirstLifeDto.getKookminType()));
        verificationRecordSpecialKookminFirstLife.setFirstRankHistoryYn(verificationRecordSpecialKookminFirstLifeDto.getFirstRankHistoryYn());
        verificationRecordSpecialKookminFirstLife.setSibilingSupportYn(verificationRecordSpecialKookminFirstLifeDto.getSibilingSupportYn());
        verificationRecordSpecialKookminFirstLifeRepository.save(verificationRecordSpecialKookminFirstLife);

        return ResponseEntity.ok(verificationRecordSpecialKookminFirstLifeDto);
    }
}