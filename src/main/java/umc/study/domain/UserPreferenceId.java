package umc.study.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserPreferenceId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long preference;
    private Long user;
}