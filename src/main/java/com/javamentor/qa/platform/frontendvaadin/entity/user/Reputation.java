package com.javamentor.qa.platform.frontendvaadin.entity.user;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reputation implements Serializable {
    private static final long serialVersionUID = 7177182244933788025L;
    private Long id;

    private LocalDateTime persistDate;

    private User user;

    private Integer count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Reputation reputation = (Reputation) o;
        return Objects.equals(id, reputation.id) &&
                Objects.equals(persistDate, reputation.persistDate) &&
                Objects.equals(count, reputation.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persistDate, count);
    }
}
