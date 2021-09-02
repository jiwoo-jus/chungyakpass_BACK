package com.hanium.chungyakpassback.handler;

import com.hanium.chungyakpassback.enumtype.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}
