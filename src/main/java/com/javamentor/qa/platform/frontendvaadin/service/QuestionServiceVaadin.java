package com.javamentor.qa.platform.frontendvaadin.service;

import com.javamentor.qa.platform.frontendvaadin.dto.PageDto;
import com.javamentor.qa.platform.frontendvaadin.dto.QuestionDto;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import java.util.*;

@Service
public class QuestionServiceVaadin {
//    @Value("${server.port}")
//    private String serverPort;
    public QuestionDto getQuestionsAsync(int questionId) {
        if ((Integer)questionId == null) questionId = 155;

        RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:5557/api/question/" + questionId);

        return spec.retrieve().toEntity(QuestionDto.class).block().getBody();
    }

    public List<QuestionDto> getQuestionList(int page, int size) {
        Map<String, Integer> params = new HashMap<>();

        params.put("page", page);
        params.put("size", size);
//        BodyInserter<MultiValueMap, ClientHttpRequest> inserter2 = BodyInserters.fromMultipartData(params);
//        RequestHeadersSpec<?> spec = WebClient.create().get()
//                .uri("http://localhost:5557/api/question", params);
//
//        PageDto<QuestionDto, Object> resultList = Objects.requireNonNull(spec.retrieve().toEntity(PageDto.class).block()).getBody();
//        List<QuestionDto> res = resultList.getItems();
//        if (res == null) {
        List<QuestionDto> res = new ArrayList<>();
            res.add(getQuestionsAsync(169));
            res.add(getQuestionsAsync(183));
//        }
        return res;
    }
}
