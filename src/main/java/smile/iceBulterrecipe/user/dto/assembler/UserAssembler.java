package smile.iceBulterrecipe.user.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.user.dto.request.AddUserReq;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class UserAssembler {
    public User toEntity(AddUserReq userInfo) {
        return User.builder()
                .userIdx(userInfo.getUserIdx())
                .nickname(userInfo.getNickname())
                .profileImg(userInfo.getProfileImg())
                .build();
    }
}
