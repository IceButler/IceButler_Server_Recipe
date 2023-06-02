package smile.iceBulterrecipe.admin.dto.response;

import lombok.*;


import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor

public class UserRecipeReportRes {
    private Long recipeReportIdx;
    private String recipeName;
    private String reason;
    private String reportDate;

    @Builder
    public UserRecipeReportRes(Long recipeReportIdx,String recipeName, String reason, String reportDate){
        this.recipeReportIdx=recipeReportIdx;
        this.recipeName=recipeName;
        this.reason=reason;
        this.reportDate=reportDate;
    }

    public static UserRecipeReportRes toDto(Long recipeReportIdx,String recipeName, String reason,String reportDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 출력 형식 지정

        return UserRecipeReportRes.builder()
                .recipeReportIdx(recipeReportIdx)
                .recipeName(recipeName)
                .reason(reason)
                .reportDate(reportDate)
                .build();


    }
}
