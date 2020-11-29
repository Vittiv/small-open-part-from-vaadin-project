package com.javamentor.qa.platform.frontendvaadin.entity.question.answer;

import com.javamentor.qa.platform.frontendvaadin.entity.Comment;
import com.javamentor.qa.platform.frontendvaadin.entity.CommentType;
import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentAnswer implements Serializable {

    private static final long serialVersionUID = 1350517718937674427L;
    private Long id;

    private Comment comment = new Comment(CommentType.ANSWER);

    private Answer answer;

    public CommentAnswer(String text, User user) {
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
//        if (this.comment.getCommentType() != CommentType.ANSWER) {
//            throw new ApiRequestException("У экземпляра Comment, связанного с CommentAnswer, " +
//                    "поле commentType должно принимать значение CommentType.ANSWER");
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentAnswer that = (CommentAnswer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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
