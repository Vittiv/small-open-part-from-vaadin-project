package com.javamentor.qa.platform.frontendvaadin.service;

import com.javamentor.qa.platform.frontendvaadin.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

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
}
