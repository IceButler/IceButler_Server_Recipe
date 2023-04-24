package smile.iceBulterrecipe.user.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.user.dto.request.UserReq;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class UserAssembler {
    public User toEntity(UserReq userInfo) {
        return User.builder()
                .userIdx(userInfo.getUserIdx())
                .nickname(userInfo.getNickname())
                .profileImgKey(userInfo.getProfileImgKey())
                .build();
    }
}
