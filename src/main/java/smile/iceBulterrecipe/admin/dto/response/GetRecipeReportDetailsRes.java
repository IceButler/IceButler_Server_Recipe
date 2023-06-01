package smile.iceBulterrecipe.admin.dto.response;

import lombok.*;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.dto.response.CookeryRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeFoodRes;
import smile.iceBulterrecipe.recipe.entity.Cookery;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeFood;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class GetRecipeReportDetailsRes {
    private Long recipeReportIdx;
    private Long recipeIdx;
    private String author;
    private String reason;
    private String reporter;
    private String reportDate;
    private String memo;
    private String recipeImgUrl;
    private String recipeName;
    private String recipeCategory;
    private int quantity;
    private Long leadTime;
    private List<RecipeFoodRes> recipeFoods;
    private List<CookeryRes> cookery;

    public static GetRecipeReportDetailsRes toDto(RecipeReport recipeReport,List<RecipeFood> recipeFoods, List<Cookery> cookery){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 출력 형식 지정

        GetRecipeReportDetailsRes getRecipeReportDetailsRes=new GetRecipeReportDetailsRes();
        getRecipeReportDetailsRes.recipeReportIdx=recipeReport.getRecipeReportIdx();
        getRecipeReportDetailsRes.recipeIdx=recipeReport.getRecipe().getRecipeIdx();
        getRecipeReportDetailsRes.author=recipeReport.getRecipe().getUser().getNickname();
        getRecipeReportDetailsRes.reason=recipeReport.getReason().getReason();
        getRecipeReportDetailsRes.reporter=recipeReport.getUser().getNickname();
        getRecipeReportDetailsRes.reportDate=recipeReport.getCreatedAt().format(formatter);
        getRecipeReportDetailsRes.memo=recipeReport.getMemo();
        getRecipeReportDetailsRes.recipeImgUrl = AwsS3ImageUrlUtils.toUrl(recipeReport.getRecipe().getRecipeImgKey());
        getRecipeReportDetailsRes.recipeName = recipeReport.getRecipe().getRecipeName();
        getRecipeReportDetailsRes.recipeCategory = recipeReport.getRecipe().getRecipeCategory().getCategory();
        getRecipeReportDetailsRes.quantity = recipeReport.getRecipe().getQuantity();
        getRecipeReportDetailsRes.leadTime = recipeReport.getRecipe().getLeadTime();
        getRecipeReportDetailsRes.recipeFoods = recipeFoods.stream().map(m -> RecipeFoodRes.toDto(m.getFoodDetail(), m.getFood().getFoodName())).collect(Collectors.toList());
        getRecipeReportDetailsRes.cookery = cookery.stream().map(m -> CookeryRes.toDto(m.getDescription(), m.getCookeryImgKey(), m.getNextIdx())).collect(Collectors.toList());
        return getRecipeReportDetailsRes;
    }
}
