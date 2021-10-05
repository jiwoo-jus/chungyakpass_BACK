package com.hanium.chungyakpassback.service.apt;//package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.apt.*;
import com.hanium.chungyakpassback.entity.apt.*;
import com.hanium.chungyakpassback.repository.apt.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ApiDetailExplorer5 {
    private final AptInfoRepository aptInfoRepository;
    private final AptInfoAmountRepository aptInfoAmountRepository;
    private final AptInfoTargetRepository aptInfoTargetRepository;
    private final AptInfoReceiptRepository aptInfoReceiptRepository;
    private final AptInfoTargetSpecialRepository aptInfoTargetSpecialRepository;
    public static int INDENT_FACTOR = 4;
    public static String jsonPrettyPrintString;
    public static String salesInformation  = "http://openapi.reb.or.kr/OpenAPI_ToolInstallPackage/service/rest/ApplyhomeInfoSvc/getLttotPblancList";//분양정보조회
    public static String detailInformationByHousingType = "http://openapi.reb.or.kr/OpenAPI_ToolInstallPackage/service/rest/ApplyhomeInfoSvc/getAPTLttotPblancMdl";//주택형별 분양정보
    public static String detailSalesInformation = "http://openapi.reb.or.kr/OpenAPI_ToolInstallPackage/service/rest/ApplyhomeInfoSvc/getAPTLttotPblancDetail";//detailSalesInformation
    public static int cout;
    String date = YearMonth.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

    // manageNo가 없을 때는 '0' 값을 넣어주어야 한다.
    public String GetAptApi(String aptUrl, int pageNo, int manageNo) {
        try {
            StringBuilder urlBuilder = new StringBuilder(aptUrl); /*URL*///주택번호와 url을 받는다.
            urlBuilder.append("?").append(URLEncoder.encode("ServiceKey", StandardCharsets.UTF_8)).append("=r%2B2HI8hwDijPaCvVzQvg1O6birty3yCNr1QCk30cBuFXESl9etAWiSqbfS8cCStGjXXcfT2yfcfXlEgViCgMmg%3D%3D"); /*Service Key*/

            urlBuilder.append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(pageNo), StandardCharsets.UTF_8));

            if (manageNo == 0) {
                urlBuilder.append("&").append(URLEncoder.encode("startmonth", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("202108", StandardCharsets.UTF_8)); /*월 단위 모집공고일 (검색시작월)*/
                urlBuilder.append("&").append(URLEncoder.encode("endmonth", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(date, StandardCharsets.UTF_8)); /*월 단위 모집공고일 (검색종료월, 최대 12개월)*/
            } else {
                urlBuilder.append("&").append(URLEncoder.encode("houseManageNo", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(manageNo), StandardCharsets.UTF_8)); /*주택관리번호*/
                urlBuilder.append("&").append(URLEncoder.encode("pblancNo", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(String.valueOf(manageNo), StandardCharsets.UTF_8)); /*공고번호*/
            }

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());//xml을 json으로 변환한다.
            jsonPrettyPrintString = xmlJSONObj.toString(INDENT_FACTOR);//json을 예쁘게 변환


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!");
        }
        return jsonPrettyPrintString;
    }

    public void apiDetailExplorer5() throws IOException {
        // 아래의 전체 코드가 일정한 시간간격을 두고 실행되어야한다.
        List<Integer> numbers = new ArrayList<>();//주택관리번호만 따로 리스트에 저장
        List<AptInfoDto> aptInfoDtoList = new ArrayList<>();
        List<AptInfoTargetDto> aptInfoTargetDtoList = new ArrayList<>();
        List<AptInfoTargetSpecialDto> aptInfoTargetSpecialDtoList = new ArrayList<>();
        List<AptInfoAmountDto> aptInfoAmountDtoList = new ArrayList<>();
        List<AptInfoReceiptDto> aptInfoReceiptDtoList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        List<CrawlingAptInfoDto> crawlingAptInfoDtoList = new ArrayList<>();

        // 아파트분양정보 2Dto 정보를 리스트로 담는다.
        List<AptInfo2Dto> aptInfo2DtoList = new ArrayList<>();


        int pageNo = 1;
        // 페이지 수를 구해야한다.
        //String result = apiExplorer.AptApiData(pageNo);
        String result = GetAptApi(salesInformation, pageNo, 0);
        JSONObject rjson = new JSONObject(result);
        JSONObject response = (JSONObject) rjson.get("response");//받아온 json에서 원하는 정보를 필터링한다.
        JSONObject body = (JSONObject) response.get("body");
        //System.out.println(body);
        int totalCount = body.getInt("totalCount");
        pageNo = totalCount / 10;
        int pagesNo = totalCount % 10;
        if (pagesNo != 0) {
            pageNo = pageNo + 1;
        }

        // 공고번호만 뽑았는데
        // 문의처, 주택유형, 건설업체도 뽑아야함
        // AptInfoDto에다가 넣어주면된다.
        for (int i = 1; i <= pageNo; i++) {
            // apiExplorer.AptApiData(i);/페이지수만큼 AptApiData를 호출
            GetAptApi(salesInformation, i, 0);
            rjson = new JSONObject(jsonPrettyPrintString);
            response = (JSONObject) rjson.get("response");//받아온 json에서 원하는 정보를 필터링한다.
            body = (JSONObject) response.get("body");
            JSONObject items = body.getJSONObject("items");
            //System.out.println(items);

            if (items.get("item") instanceof JSONArray) {//item이 array형식으로 들어올때
                JSONArray item = (JSONArray) items.get("item");

                for (int j = 0; j < item.length(); j++) {//item의 크기만큼 반복
                    JSONObject itemJson = item.getJSONObject(j);
                    // houseManageNo 공고번호이다.
                    // 공고번호, 주택유형, 건설업체 정보를 가져온다.
                    AptInfo2Dto aptInfo2dto = new AptInfo2Dto(itemJson);
                    aptInfo2DtoList.add(aptInfo2dto);

                    numbers.add(aptInfo2dto.getNotificationNumber());

                    //System.out.println(numbers);
                }
            } else {//item이 object형식으로 들어올때
                JSONObject itemJson = items.getJSONObject("item");
                //int houseManageNo = itemJson.getInt("houseManageNo");//주택관리번호 뽑아냄
                AptInfo2Dto aptInfo2dto = new AptInfo2Dto(itemJson);
                aptInfo2DtoList.add(aptInfo2dto);

                numbers.add(aptInfo2dto.getNotificationNumber());

                //System.out.println(numbers);
            }


        }
        for (Integer number : numbers) {
            String spec = "https://www.applyhome.co.kr/ai/aia/selectAPTLttotPblancDetail.do?gvPgmId=AIA01M01"
                    + "&houseManageNo="
                    + number
                    + "&pblancNo="
                    + number;
            urlList.add(spec);
        }

        for (int a = 0; a < numbers.size(); a++) {
            Document getDetail = Jsoup.connect(urlList.get(a)).get();
            String content = getDetail.select("ul[class=inde_txt] li").get(0).text();
            String content1 = getDetail.select("ul[class=inde_txt]").get(2).text();
            String[] content2 = content1.split(" ");
            String string = content2[3];

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM");
//            LocalDate date = LocalDate.parse(string, formatter);

            Integer number = numbers.get(a);

            CrawlingAptInfoDto CrawlingAptInfodto = new CrawlingAptInfoDto(content, number, string);
            crawlingAptInfoDtoList.add(CrawlingAptInfodto);

        }


//        // 공고번호를 가지고 API에서 데이터를 가져온다.
        for (Integer number : numbers) {//주택관리번호 리스트를 하나씩 반복
            //String value = apiExplorer.GetAptDetail(numbers.get(i), detailSalesInformation);//주택관리번호와 분양정보 상세 조회url을 GetAptDetail메소드에 보내서 실행
            String value = GetAptApi(detailSalesInformation, 1, number);
            rjson = new JSONObject(value);//반환값을 필터링
            response = (JSONObject) rjson.get("response");
            System.out.println("response"+rjson);
            System.out.println("response"+response);
            if(response.has("body")) {
                body = (JSONObject) response.get("body");
            }
            if (body.get("items") instanceof JSONObject) {//주택번호와 url은 있는데 값이 안들어온 경우 값이 object형식으로 들어왔나 확인
                JSONObject items = (JSONObject) body.get("items");
                JSONObject itemJson = items.getJSONObject("item");//item크기만큼 dto에 저장
                String detailAddress = itemJson.getString("hssplyadres");
                String[] areaLevel = detailAddress.split(" ");
                for (String s : areaLevel) {
                    System.out.println(s.trim());
                }
                //System.out.println(items);
                System.out.println(itemJson);

                AptInfoReceiptDto AptInfoReceiptdto = new AptInfoReceiptDto(itemJson);//item크기만큼 청약접수일정1Dto에 저장
                aptInfoReceiptDtoList.add(AptInfoReceiptdto);

                AptInfoDto AptInfodto = new AptInfoDto(itemJson);

                for (CrawlingAptInfoDto CrawlingAptInfodto : crawlingAptInfoDtoList) {
                    if (CrawlingAptInfodto.notificationNumber.equals(AptInfodto.getNotificationNumber())) {
                        AptInfodto.atrophyArea = CrawlingAptInfodto.atrophyArea;
                        AptInfodto.largeDevelopmentzone = CrawlingAptInfodto.largeDevelopmentzone;
                        AptInfodto.maintenanceWork = CrawlingAptInfodto.maintenanceWork;
                        AptInfodto.publicHosingDistrict = CrawlingAptInfodto.publicHosingDistrict;
                        AptInfodto.publicRentalHousing = CrawlingAptInfodto.publicRentalHousing;
                        AptInfodto.salePriceLimit = CrawlingAptInfodto.salePriceLimit;
                        AptInfodto.scheduledOccupancy = CrawlingAptInfodto.scheduledOccupancy;
                        AptInfodto.subscriptionOverheated = CrawlingAptInfodto.subscriptionOverheated;
                        AptInfodto.speculationOverheated = CrawlingAptInfodto.speculationOverheated;
                        AptInfodto.specialActPublicHousing = CrawlingAptInfodto.specialActPublicHousing;
                    }
                }
                for (AptInfo2Dto aptInfo2dto : aptInfo2DtoList) {
                    if (aptInfo2dto.notificationNumber.equals(AptInfodto.getNotificationNumber())) {
                        AptInfodto.housingType = aptInfo2dto.housingType;
                        AptInfodto.constructionCompany = aptInfo2dto.constructionCompany;
                        AptInfodto.addressLevel1 = aptInfo2dto.addressLevel1;
                    }
                }
                aptInfoDtoList.add(AptInfodto);
            }
        }

        // 주택관리번호 리스트만큼 반복 - 13개
        for (Integer number : numbers) {
            //String value = apiExplorer.GetAptDetail(numbers.get(i), detailInformationByHousingType);//주택관리번호와 url을 GetAptDetail에 넣는다.
            String value = GetAptApi(detailInformationByHousingType, 1, number);

            rjson = new JSONObject(value);
            response = (JSONObject) rjson.get("response");
            if(response.has("body")) {
                body = (JSONObject) response.get("body");
            }
            cout++;

            // item들이 담기는 JSONObject
            List<JSONObject> objects = new ArrayList<>();
            if (body.get("items") instanceof JSONObject) {//주택번호와 url은 있는데 값이 안들어온 경우 값이 object형식으로 들어왔나 확인
                JSONObject items = body.getJSONObject("items");
                System.out.println(items);

                if (items.get("item") instanceof JSONArray) {//item이 array형식인지 확인
                    JSONArray item = (JSONArray) items.get("item");

                    // AptInfoDtoList에 있는 AptInfoDto를 순서대로 전부 불러온다.
                    for (int j = 0; j < item.length(); j++) {//item크기만큼 dto에 저장
                        JSONObject itemJson = item.getJSONObject(j);
                        objects.add(itemJson);
                    }

                    // Array 타입이 아닌 Item
                } else {
                    JSONObject itemJson = items.getJSONObject("item");//item크기만큼 dto에 저장
                    objects.add(itemJson);
                }
            }


                // 각 주택관리 번호별 총 item개수
                int totalCount1 = body.getInt("totalCount");
                // totalCount가 10개 이상이면 페이지 넘버를 1개 추가한다
                if (totalCount1 > 10) {
                    // 페이지 넘버를 1개 추가해서 값을 가져온다.
                    String value2 = GetAptApi(detailInformationByHousingType, 2, number);
                    rjson = new JSONObject(value2);
                    response = (JSONObject) rjson.get("response");
                    body = (JSONObject) response.get("body");
                    cout++;

                    if (body.get("items") instanceof JSONObject) {//주택번호와 url은 있는데 값이 안들어온 경우 값이 object형식으로 들어왔나 확인
                        JSONObject items2 = body.getJSONObject("items");
                        System.out.println(items2);

                        if (items2.get("item") instanceof JSONArray) {//item이 array형식인지 확인
                            JSONArray item = (JSONArray) items2.get("item");

                            // AptInfoDtoList에 있는 AptInfoDto를 순서대로 전부 불러온다.
                            for (int j = 0; j < item.length(); j++) {//item크기만큼 dto에 저장
                                JSONObject itemJson = item.getJSONObject(j);
                                objects.add(itemJson);
                            }
                        } else {
                            JSONObject itemJson = items2.getJSONObject("item");//item크기만큼 dto에 저장
                            objects.add(itemJson);
                        }
                    }
                }


            // AptInfoDtoList의 첫번째 값을 objects 개수 만큼 돌린다.
            for (AptInfoDto AptInfodto : aptInfoDtoList) {
                if (AptInfodto.getNotificationNumber().equals(number)) {
                    for (JSONObject item : objects) {
                        // ID만 받는다.
                        //아파트분양정보_공급대상1Dto 아파트분양정보_공급대상1dto = new 아파트분양정보_공급대상1Dto(분양정보1.공고번호, item);

                        AptInfoTargetDto AptInfoTargetdto = new AptInfoTargetDto(item);
                        aptInfoTargetDtoList.add(AptInfoTargetdto);

                        AptInfoAmountDto aptInfoAmountdto = new AptInfoAmountDto(item);
                        aptInfoAmountDtoList.add(aptInfoAmountdto);

                        AptInfoTargetSpecialDto AptInfoTargetSpecialdto = new AptInfoTargetSpecialDto(item);
                        aptInfoTargetSpecialDtoList.add(AptInfoTargetSpecialdto);
                    }
                }
            }
        }

        // 아파트분양정보 테이블에 공고번호가 중복되는 값이 들어가면 안된다.
        for (AptInfoDto aptInfodto : aptInfoDtoList) {
            List<AptInfo> aptInfoList = aptInfoDtoList.stream()
                    .map(AptInfoDto::toEntity)
                    .collect(Collectors.toList());

            aptInfoRepository.findById(aptInfodto.getNotificationNumber()).orElseGet(() -> {
                aptInfoRepository.saveAll(aptInfoList);
                for (AptInfoAmountDto AptInfoAmountdto : aptInfoAmountDtoList) {
                    AptInfo aptInfo = aptInfoRepository.findById(AptInfoAmountdto.getNotificationNumber()).get();
                    AptInfoAmount aptInfoAmount = AptInfoAmount.builder()
                            .aptInfo(aptInfo)
                            .supplyAmount(AptInfoAmountdto.getSupplyAmount())
                            .housingType(AptInfoAmountdto.getHousingType())
                            .build();
                    aptInfoAmountRepository.save(aptInfoAmount);
                }
                for (AptInfoReceiptDto AptInfoReceiptdto : aptInfoReceiptDtoList) {
                    AptInfo aptInfo = aptInfoRepository.findById(AptInfoReceiptdto.getNotificationNumber()).get();
                    AptInfoReceipt aptInfoReceipt = AptInfoReceipt.builder()
                            .aptInfo(aptInfo)
                            .specialReceptionStartDate(AptInfoReceiptdto.getSpecialReceptionStartDate())
                            .specialReceptionEndDate(AptInfoReceiptdto.getSpecialReceptionEndDate())
                            .priorityApplicableAreaStart(AptInfoReceiptdto.getPriorityApplicableAreaStart())
                            .priorityApplicableAreaEnd(AptInfoReceiptdto.getPriorityApplicableAreaEnd())
                            .priorityGyeonggiAreaStart(AptInfoReceiptdto.getPriorityGyeonggiAreaStart())
                            .priorityGyeonggiAreaEnd(AptInfoReceiptdto.getPriorityGyeonggiAreaEnd())
                            .priorityOtherAreaStart(AptInfoReceiptdto.getPriorityOtherAreaStart())
                            .priorityOtherAreaEnd(AptInfoReceiptdto.getPriorityOtherAreaEnd())
                            .secondApplicableAreaStart(AptInfoReceiptdto.getSecondApplicableAreaStart())
                            .secondApplicableAreaEnd(AptInfoReceiptdto.getSecondApplicableAreaEnd())
                            .secondGyeonggiAreaStart(AptInfoReceiptdto.getSecondGyeonggiAreaStart())
                            .secondGyeonggiAreaEnd(AptInfoReceiptdto.getSecondGyeonggiAreaEnd())
                            .secondOtherAreaStart(AptInfoReceiptdto.getSecondOtherAreaStart())
                            .secondOtherAreaEnd(AptInfoReceiptdto.getSecondOtherAreaEnd())
                            .homepage(AptInfoReceiptdto.getHomepage())
                            .build();
                    aptInfoReceiptRepository.save(aptInfoReceipt);
                }
                for (AptInfoTargetSpecialDto AptInfoTargetSpecialdto : aptInfoTargetSpecialDtoList) {
                    AptInfo aptInfo = aptInfoRepository.findById(AptInfoTargetSpecialdto.getNotificationNumber()).get();
                    AptInfoTargetSpecial aptInfoTargetSpecial = AptInfoTargetSpecial.builder()
                    .aptInfo(aptInfo)
                    .housingType(AptInfoTargetSpecialdto.getHousingType())
                    .supplyMultiChildHousehold(AptInfoTargetSpecialdto.getSupplyMultiChildHousehold())
                    .supplyNewlyMarriedCouple(AptInfoTargetSpecialdto.getSupplyNewlyMarriedCouple())
                    .supplyOldParentSupport(AptInfoTargetSpecialdto.getSupplyOldParentSupport())
                    .supplyFirstLife(AptInfoTargetSpecialdto.getSupplyFirstLife())
                    .supplyInstitutionalRecommendation(AptInfoTargetSpecialdto.getSupplyInstitutionalRecommendation())
                    .supplyTransferAgency(AptInfoTargetSpecialdto.getSupplyTransferAgency())
                    .supplyOther(AptInfoTargetSpecialdto.getSupplyOther())
                    .build();
                    aptInfoTargetSpecialRepository.save(aptInfoTargetSpecial);
        }

        // 아파트분양정보 테이블에 공고번호가 이미 있다면 공급대상 테이블에 해당공고번호와 관련된 공급대상은 추가하지 않는다.
        for (AptInfoTargetDto AptInfoTargetdto : aptInfoTargetDtoList) {
            AptInfo aptInfo = aptInfoRepository.findById(AptInfoTargetdto.getNotificationNumber()).get();
            AptInfoTarget aptInfoTarget = AptInfoTarget.builder()
                    .aptInfo(aptInfo)
                    .housingType(AptInfoTargetdto.getHousingType())
                    .supplyArea(AptInfoTargetdto.getSupplyArea())
                    .supplyGeneral(AptInfoTargetdto.getSupplyGeneral())
                    .supplySpecial(AptInfoTargetdto.getSupplySpecial())
                    .supplyTotal(AptInfoTargetdto.getSupplyTotal())
                    .build();
            aptInfoTargetRepository.save(aptInfoTarget);
        }
                return null;
            });
        }




    }
}

