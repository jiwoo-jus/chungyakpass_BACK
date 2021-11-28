package com.hanium.chungyakpassback.dto.authority;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

   @NotNull
   @Size(min = 3, max = 50)
   private String email; //이메일

   @NotNull
   @Size(min = 3, max = 100)
   private String password; //비밀번호
}
