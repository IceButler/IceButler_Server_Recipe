package smile.iceBulterrecipe.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddUserReq {
    private Long userIdx;
    private String nickname;
    private String profileImg;

    @Builder
    public AddUserReq(Long userIdx, String nickname, String profileImg) {
        this.userIdx = userIdx;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
