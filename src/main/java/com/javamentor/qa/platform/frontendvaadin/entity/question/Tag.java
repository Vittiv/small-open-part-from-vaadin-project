package com.javamentor.qa.platform.frontendvaadin.entity.question;

//import com.javamentor.qa.platform.exception.ConstrainException;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag implements Serializable {

    private static final long serialVersionUID = 6264105282197120461L;
    private Long id;

    private String name;

    private String description;

    private LocalDateTime persistDateTime;

    private List<Question> questions;

    private void prePersistFunction() {
        checkConstraints();
    }

    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
//        if (this.description.isEmpty()) {
//            throw new ConstrainException("Поле description не должно быть пустым");
//        }
//        if (this.name.isEmpty()) {
//            throw new ConstrainException("Поле name не должно быть пустым");
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name) &&
                Objects.equals(description, tag.description) &&
                Objects.equals(persistDateTime, tag.persistDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, persistDateTime);
    }


}
