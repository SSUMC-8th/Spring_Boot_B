package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.TermType;
import umc.spring.domain.mapping.MemberTerms;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 225)
    private String description;

    @Enumerated(EnumType.STRING)
    private TermType type;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<MemberTerms> memberTermsList = new ArrayList<>();
}
