package smile.iceBulterrecipe.admin.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.user.entity.User;

@NoArgsConstructor
@Data
public class UserResponse {
    private Long userIdx;
    private String nickname;
    private String email;
    private int reportCount;


    public static UserResponse toDto(User user)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.userIdx = user.getUserIdx();
        userResponse.nickname = user.getNickname();
        userResponse.email = user.getEmail();
        userResponse.reportCount = 0;
        return userResponse;
    }

    public void upReportCount() {
        this.reportCount ++;
    }
}
