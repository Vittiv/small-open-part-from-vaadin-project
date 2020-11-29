package com.javamentor.qa.platform.frontendvaadin.entity.question;

//import com.javamentor.qa.platform.exception.ConstrainException;
import com.javamentor.qa.platform.frontendvaadin.entity.question.answer.Answer;
import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import com.javamentor.qa.platform.frontendvaadin.entity.user.UserFavoriteQuestion;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements Serializable {

    private static final long serialVersionUID = -4612026867697897418L;
    private Long id;

    private String title;


    private Integer viewCount = 0;

    private String description;

    private LocalDateTime persistDateTime;

    private User user;

    private  List<Tag> tags;

    private LocalDateTime lastUpdateDateTime;

    private Boolean isDeleted;

    private List<Answer> answers;

    private List<CommentQuestion> commentQuestions;

   private List<UserFavoriteQuestion> userFavoriteQuestions;

    private List<VoteQuestion> voteQuestions;

    private void prePersistFunction() {
        checkConstraints();
    }

    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
//        if (this.tags == null || this.tags.isEmpty()) {
//            throw new ConstrainException("Экземпляр Question должен иметь в поле tags хотя бы один элемент");
//        }
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
//        try {
//            if (this.user.getId() <= 0) {
//                throw new EntityNotFoundException("User id must be > 0 on create or update.");
//            }
//        } catch (NullPointerException e) {
//            throw new EntityNotFoundException("User id must be not null on create.");
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(title, question.title) &&
                Objects.equals(viewCount, question.viewCount) &&
                Objects.equals(description, question.description) &&
                Objects.equals(persistDateTime, question.persistDateTime) &&
                Objects.equals(user, question.user) &&
                Objects.equals(tags, question.tags) &&
                Objects.equals(lastUpdateDateTime, question.lastUpdateDateTime) &&
                Objects.equals(isDeleted, question.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, viewCount, description, persistDateTime, user, tags, lastUpdateDateTime, isDeleted);
    }
}
