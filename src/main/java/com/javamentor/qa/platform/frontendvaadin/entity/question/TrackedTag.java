package com.javamentor.qa.platform.frontendvaadin.entity.question;

import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackedTag implements Serializable {


    private static final long serialVersionUID = 6056471660108076229L;
    private Long id;

    private Tag trackedTag;

    private User user;

    private LocalDateTime persistDateTime;
}
