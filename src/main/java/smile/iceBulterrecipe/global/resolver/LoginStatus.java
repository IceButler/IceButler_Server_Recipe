package smile.iceBulterrecipe.global.resolver;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginStatus {
    private Boolean isLogin;
    private Long userIdx;
    @Builder
    public LoginStatus(Boolean isLogin, Long userIdx) {
        this.isLogin = isLogin;
        this.userIdx = userIdx;
    }
    public static LoginStatus getNotLoginStatus() {
        return new LoginStatus(false, null);
    }
}
