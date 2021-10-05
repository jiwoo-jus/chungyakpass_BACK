package com.hanium.chungyakpassback.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400
     */
    BAD_REQUEST_RESOURCE(BAD_REQUEST, "잘못된 요청입니다"),
    BAD_REQUEST_USER_AND_USER_HOUSE(BAD_REQUEST, "회원은 회원 본인의 세대에 속해야 합니다"),
    BAD_REQUEST_SPOUSE_AND_SPOUSE_HOUSE(BAD_REQUEST, "배우자 분리세대 생성 시 배우자는 배우자 분리세대에 속해야 합니다"),
    BAD_REQUEST_BANKBOOK(BAD_REQUEST, "청약가능한 청약통장 유형이 아닙니다."),
    BAD_REQUEST_HOMELESS(BAD_REQUEST, "무주택 세대구성원이 아닙니다."),
    BAD_REQUEST_LACK_BANKBOOK(BAD_REQUEST, "청약통장 납입액이 부족합니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    RESOURCE_NOT_FOUND(NOT_FOUND, "해당 자원을 찾을 수 없습니다"),
    NOT_FOUND_BANKBOOK(NOT_FOUND, "해당 청약통장을 찾을 수 없습니다"),
    NOT_FOUND_HOUSE(NOT_FOUND, "해당 세대를 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_HOLDER(NOT_FOUND, "해당 세대주를 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_MEMBER(NOT_FOUND, "해당 세대구성원을 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_MEMBER_PROPERTY(NOT_FOUND, "해당 세대구성원자산을 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_MEMBER_CHUNGYAK(NOT_FOUND, "해당 세대구성원청약이력을 찾을 수 없습니다"),
    NOT_FOUND_HOUSE_MEMBER_CHUNGYAK_RESTRICTION(NOT_FOUND, "해당 세대구성원청약제한사항을 찾을 수 없습니다"),
    NOT_FOUND_ADDRESS_LEVEL1(NOT_FOUND, "지역레벨1을 찾을 수 없습니다"),
    NOT_FOUND_ADDRESS_LEVEL2(NOT_FOUND, "지역레벨2를 찾을 수 없습니다"),
    NOT_FOUND_RELATION(NOT_FOUND, "관계를 찾을 수 없습니다"),
    NOT_FOUND_BIRTHDAY(NOT_FOUND, "회원의 생년월일을 찾을 수 없습니다"),
    NOT_FOUND_APT(NOT_FOUND, "주택 정보를 찾을 수 없습니다"),
    NOT_FOUND_MARRIAGES(NOT_FOUND, "혼인신고일을 찾을 수 없습니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
    DUPLICATE_EMAIL(CONFLICT, "이미 가입되어 있는 이메일입니다"),
    DUPLICATE_BANKBOOK(CONFLICT, "청약통장이 이미 등록되어 있습니다"),
    DUPLICATE_HOUSE(CONFLICT, "해당 세대가 이미 등록되어 있습니다"),
    DUPLICATE_RELATION(CONFLICT, "해당 관계의 세대구성원이 이미 등록되어 있습니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
