package smile.iceBulterrecipe.global.resolver;


import lombok.Builder;
import lombok.Data;

@Data
public class AdminLoginStatus {
    private Boolean isLogin;
    private Long adminIdx;
    @Builder
    public AdminLoginStatus(Boolean isLogin, Long adminIdx) {
        this.isLogin = isLogin;
        this.adminIdx = adminIdx;
    }
    public static AdminLoginStatus getNotAdminLoginStatus() {
        return new AdminLoginStatus(false, null);
    }
}