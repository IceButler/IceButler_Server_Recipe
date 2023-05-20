package smile.iceBulterrecipe.admin.dto;

import lombok.*;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class GetRecipeReportRes {
    private Long recipeReportIdx;
    private String recipeName;
    private String author;
    private Reason reason;
    private String reporter;
    private LocalDateTime reportDate;

    public static GetRecipeReportRes toDto(int rowNumber, RecipeReport recipeReport){
        return new GetRecipeReportRes(
                (long) rowNumber,
                recipeReport.getRecipe().getRecipeName(),
                recipeReport.getRecipe().getUser().getNickname(),
                recipeReport.getReason(),
                recipeReport.getUser().getNickname(),
                recipeReport.getCreatedAt()
        );
    }
}
