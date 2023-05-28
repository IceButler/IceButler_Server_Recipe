package smile.iceBulterrecipe.admin.dto.response;

import lombok.*;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@Builder
public class GetRecipeReportRes {
    private Long recipeReportIdx;
    private String recipeName;
    private String author;
    private Reason reason;
    private String reporter;
    private String reportDate;

    public static GetRecipeReportRes toDto(int rowNumber, RecipeReport recipeReport){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 출력 형식 지정

        return new GetRecipeReportRes(
                (long) rowNumber,
                recipeReport.getRecipe().getRecipeName(),
                recipeReport.getRecipe().getUser().getNickname(),
                recipeReport.getReason(),
                recipeReport.getUser().getNickname(),
                recipeReport.getCreatedAt().format(formatter)
        );
    }
}
