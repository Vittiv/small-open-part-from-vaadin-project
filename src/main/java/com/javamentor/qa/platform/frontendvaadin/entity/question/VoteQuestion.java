package com.javamentor.qa.platform.frontendvaadin.entity.question;

//import com.javamentor.qa.platform.exception.ConstrainException;
import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteQuestion implements Serializable {

    private static final long serialVersionUID = 6479035497338780810L;

    private Long id;

    private User user;

    private Question question;

    private LocalDateTime localDateTime = LocalDateTime.now();

    private int vote;

    private void prePersistFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
//        if (vote != 1 && vote != -1) {
//            throw new ConstrainException("В сущности VoteQuestion допускается передача значения в поле vote только 1 или -1");
//        }
    }
}
