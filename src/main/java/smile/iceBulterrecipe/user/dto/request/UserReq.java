package smile.iceBulterrecipe.user.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserReq {
    private Long userIdx;
    private String nickname;
    private String profileImgKey;
    private String email;
}
