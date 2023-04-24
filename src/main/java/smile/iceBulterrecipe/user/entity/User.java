package smile.iceBulterrecipe.user.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.BaseEntity;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @Column(nullable = false)
    private Long userIdx;
    private String nickname;
    private String profileImg;

    @Builder
    public User(Long userIdx, String nickname, String profileImg) {
        this.userIdx = userIdx;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
