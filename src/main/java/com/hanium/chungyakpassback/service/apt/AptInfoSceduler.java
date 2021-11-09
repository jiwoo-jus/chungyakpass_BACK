package com.hanium.chungyakpassback.service.apt;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성합니다.
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
public class AptInfoSceduler {
    private final ApiDetailExplorer5 apiDetailExplorer5;
 @Scheduled(cron="0 1 * * * ?")
    public void saveData() throws IOException {
        apiDetailExplorer5.apiDetailExplorer5();
    }
}
