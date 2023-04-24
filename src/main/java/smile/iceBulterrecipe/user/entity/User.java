package smile.iceBulterrecipe.user.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.global.entityListener.UserEntityListener;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE user SET status = 'inactive', last_modified_date = current_timestamp WHERE user_idx = ?")
@EntityListeners(UserEntityListener.class)
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
