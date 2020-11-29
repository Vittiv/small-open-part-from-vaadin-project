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
public class IgnoredTag  implements Serializable {


    private static final long serialVersionUID = 7657497719741178473L;
    private Long id;

    private Tag ignoredTag;

    private User user;

    private LocalDateTime persistDateTime;

}
