package com.example.capstone.controller;

import com.example.capstone.domain.IotPlatform;
import com.example.capstone.domain.QuestionRequestDto;
import com.example.capstone.repository.IotPlatformRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatGptApiController {
    private final String BASE_URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "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";
    private final IotPlatformRepository iotPlatformRepository;

    private final double bestDO = 6.5;
    private final double bestTemp = 17.0;

    @PostMapping("/userQuestion")
    public String question(@RequestBody QuestionRequestDto userQuestion) {
        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                .build();

        IotPlatform iot = iotPlatformRepository.findTop1ByOrderByDateTimeDesc();

        String question = "광어 양식환경에서 " +
                "적정 수온이 " + bestTemp +
                "이고, 적정 용존산소량이 " + bestDO +
                "이고, 현재 용존산소가 " + iot.getDO() +
                "이고, 수온이 " + iot.getTemp() +
                "일 때 " + userQuestion.getUserQuestion()
                + " temperature:0.8";

        String requestBody = "{" +
                "\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\": [" +
                "{" +
                "\"role\": \"user\"," +
                "\"content\": \"" + question + "\"" +
                "}" +
                "]" +
                "}";

        System.out.println(requestBody);


        return webClient.post()
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @PostMapping("/defaultQuestion")
    public String giveQuestion() {
        IotPlatform iot = iotPlatformRepository.findTop1ByOrderByDateTimeDesc();

        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                .build();

        String defaultQuestion = "광어 양식환경에서 " +
                "적정 용존산소량이 " + bestDO +
                "이고, 용존산소가 기준보다 낮으면 에어레이터를 가동시켜야하고 높으면 에어레이터를 가동시키면 안됩니다." +
                "현재 용존산소가 " + iot.getDO() +
                "이고, 수온이 " + iot.getTemp() +
                "일 때 에어레이터를 가동시키는 것이 좋을까요?" +
                " temperature:0.8" +
                " top-k:0.3";

        String requestBody = "{" +
                "\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\": [" +
                "{" +
                "\"role\": \"user\"," +
                "\"content\": \"" + defaultQuestion + "\"" +
                "}" +
                "]" +
                "}";

        return webClient.post()
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}