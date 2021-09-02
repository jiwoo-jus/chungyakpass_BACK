package com.hanium.chungyakpassback.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    RESOURCE_NOT_FOUND(NOT_FOUND, "해당 자원을 찾을 수 없습니다"),
    NOT_FOUND_BANKBOOK(NOT_FOUND, "해당 청약통장을 찾을 수 없습니다"),
    NOT_FOUND_HOUSE(NOT_FOUND, "해당 세대를 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_MEMBER(NOT_FOUND, "해당 세대구성원을 찾을 수 없습니다"),
    NOT_FOUND_ADDRESS_LEVEL1(NOT_FOUND, "지역레벨1을 찾을 수 없습니다"),
    NOT_FOUND_ADDRESS_LEVEL2(NOT_FOUND, "지역레벨2를 찾을 수 없습니다"),
    NOT_FOUND_RELATION(NOT_FOUND, "관계를 찾을 수 없습니다"),
    NOT_FOUND_BIRTHDAY(NOT_FOUND, "회원의 생년월일을 찾을 수 없습니다"),
    NOT_FOUND_APT(NOT_FOUND, "주택 정보를 찾을 수 없습니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
    DUPLICATE_EMAIL(CONFLICT, "이미 가입되어 있는 이메일입니다"),
    DUPLICATE_BANKBOOK(CONFLICT, "청약통장이 이미 등록되어 있습니다"),
    DUPLICATE_HOUSE(CONFLICT, "해당 세대가 이미 등록되어 있습니다"),
    DUPLICATE_RELATION(CONFLICT, "해당 관계의 세대구성원이 이미 등록되어 있습니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
