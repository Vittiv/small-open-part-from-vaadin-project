package com.javamentor.qa.platform.frontendvaadin.entity.user;

import com.javamentor.qa.platform.frontendvaadin.entity.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBadges implements Serializable {

    private static final long serialVersionUID = 7887575908980210093L;
    private Long id;

    private Boolean ready;

    private User user;

    private Badge badge;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        UserBadges that = (UserBadges) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(ready, that.ready);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ready);
    }
}

