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
    private String profileImgKey;

    @Builder
    public User(Long userIdx, String nickname, String profileImgKey) {
        this.userIdx = userIdx;
        this.nickname = nickname;
        this.profileImgKey = profileImgKey;
    }

    public void modifyProfile(String nickname, String profileImgKey) {
        this.nickname = nickname;
        this.profileImgKey = profileImgKey;
    }

}
