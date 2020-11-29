package com.javamentor.qa.platform.frontendvaadin.entity;

import com.javamentor.qa.platform.frontendvaadin.entity.user.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment implements Serializable {

    private static final long serialVersionUID = -5103534612783672462L;
    private Long id;


    private String text;

    private CommentType commentType;

    private LocalDateTime persistDateTime;

    private LocalDateTime lastUpdateDateTime;

    private User user;

    public Comment(CommentType commentType) {
        this.commentType = commentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(persistDateTime, comment.persistDateTime) &&
                Objects.equals(lastUpdateDateTime, comment.lastUpdateDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, persistDateTime, lastUpdateDateTime);
    }
}
