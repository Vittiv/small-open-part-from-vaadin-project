package com.javamentor.qa.platform.frontendvaadin.entity.question;

//import com.javamentor.qa.platform.exception.ApiRequestException;
import com.javamentor.qa.platform.frontendvaadin.entity.Comment;
import com.javamentor.qa.platform.frontendvaadin.entity.CommentType;
import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentQuestion implements Serializable {

    private static final long serialVersionUID = -5612025796504179957L;
    private Long id;

    private Comment comment = new Comment(CommentType.QUESTION);

    private Question question;

    public CommentQuestion(String text, User user) {
        comment.setText(text);
        comment.setUser(user);
    }

    private void prePersistFunction() {
        checkConstraints();
    }

    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
//        if (this.comment.getCommentType() != CommentType.QUESTION) {
//            throw new ApiRequestException("У экземпляра Comment, связанного с CommentQuestion, " +
//                    "поле commentType должно принимать значение CommentType.QUESTION");
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentQuestion that = (CommentQuestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setText(String text){
        comment.setText(text);
    }

    public void setUser(User user){
        comment.setUser(user);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
