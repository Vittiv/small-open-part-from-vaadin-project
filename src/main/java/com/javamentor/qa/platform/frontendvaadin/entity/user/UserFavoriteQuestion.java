package com.javamentor.qa.platform.frontendvaadin.entity.user;

import com.javamentor.qa.platform.frontendvaadin.entity.question.Question;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFavoriteQuestion implements Serializable {

    private static final long serialVersionUID = 754968028813775944L;
    private Long id;

    private LocalDateTime persistDateTime;

    private User user;

    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavoriteQuestion that = (UserFavoriteQuestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
