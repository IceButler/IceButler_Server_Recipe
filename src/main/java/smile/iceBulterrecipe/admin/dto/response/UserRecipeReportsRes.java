package smile.iceBulterrecipe.admin.dto.response;

import lombok.*;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class UserRecipeReportsRes {
    private Long userIdx;
    private List<UserRecipeReportRes> userRecipeReportResList;

    @Builder
    public UserRecipeReportsRes(Long userIdx,List<UserRecipeReportRes> userRecipeReportResList) {
        this.userIdx = userIdx;
        this.userRecipeReportResList=userRecipeReportResList;
    }
public static UserRecipeReportsRes toDto(Long userIdx, List<RecipeReport> recipeReports) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    List<UserRecipeReportRes> userRecipeReportResList = recipeReports.stream()
            .map(report -> UserRecipeReportRes.builder()
                    .recipeReportIdx(report.getRecipeReportIdx())
                    .recipeName(report.getRecipe().getRecipeName())
                    .reason(report.getReason().getReason())
                    .reportDate(report.getCreatedAt().format(formatter))
                    .build())
            .collect(Collectors.toList());

    return new UserRecipeReportsRes(userIdx, userRecipeReportResList);
}
}
