package com.javamentor.qa.platform.frontendvaadin.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Badge implements Serializable {

    private static final long serialVersionUID = 3714949012456624550L;
    private Long id;

    private String badgeName;

    private Integer reputationForMerit;

    private String description;
}
