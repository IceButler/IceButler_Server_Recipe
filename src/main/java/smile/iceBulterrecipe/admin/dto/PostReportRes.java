package smile.iceBulterrecipe.admin.dto;

import lombok.*;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostReportRes {
    private Long recipeReportIdx;
    private String recipeName;
    private String author;
    private Reason reason;
    private String reporter;
    private LocalDateTime reportDate;

    public static PostReportRes toDto(int rowNumber,RecipeReport recipeReport){
        return new PostReportRes(
                (long) rowNumber,
                recipeReport.getRecipe().getRecipeName(),
                recipeReport.getRecipe().getUser().getNickname(),
                recipeReport.getReason(),
                recipeReport.getUser().getNickname(),
                recipeReport.getCreatedAt()
        );
    }
}
