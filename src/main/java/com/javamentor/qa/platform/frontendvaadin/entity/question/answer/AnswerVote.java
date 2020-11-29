package com.javamentor.qa.platform.frontendvaadin.entity.question.answer;

import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerVote {

    private Long id;

    private User user;

    private Answer answer;

    private LocalDateTime persistDateTime;

    private Integer vote;

}
