package com.hanium.chungyakpassback.dto.vertification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponseDto {

    boolean surroundingAreaTf;
    boolean accountTf;
    Integer americanAge;
    boolean houseHolderTf;
    boolean restrictedAreaTf;
    boolean winningHistoryTf;
    boolean hasHouse;
    boolean termsOfPolicyTf;
    boolean depositAmountTf;
    boolean specialTf;

}
