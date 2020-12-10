package com.javamentor.qa.platform.frontendvaadin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javamentor.qa.platform.frontendvaadin.dto.PageDto;
import com.javamentor.qa.platform.frontendvaadin.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import java.util.*;

@Service
public class QuestionServiceVaadin {
//    @Value("${server.port}")
//    private String serverPort;
    public QuestionDto getQuestionsAsync(int questionId) {

        RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:5557/api/question/" + questionId);

        return spec.retrieve().toEntity(QuestionDto.class).block().getBody();
    }

    public List<QuestionDto> getQuestionList(int page, int size) {

        String uri = "http://localhost:5557/api/question/?page="+ page + "&size=" + size;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri(uri);
        PageDto<QuestionDto, Object> resultList = Objects.requireNonNull(spec.retrieve().toEntity(PageDto.class).block()).getBody();
        return mapper.convertValue(resultList.getItems(), new TypeReference<List<QuestionDto>>() { });
    }
}
