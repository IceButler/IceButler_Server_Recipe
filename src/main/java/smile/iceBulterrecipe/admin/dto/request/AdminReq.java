package smile.iceBulterrecipe.admin.dto.request;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AdminReq {
    private Long adminIdx;
    private String email;

    @Builder
    public AdminReq(Long adminIdx, String email) {
        this.adminIdx = adminIdx;
        this.email = email;
    }
}