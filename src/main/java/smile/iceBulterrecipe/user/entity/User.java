package smile.iceBulterrecipe.user.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.BaseEntity;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long userIdx;
    private String nickname;
    private String profileImg;
}
