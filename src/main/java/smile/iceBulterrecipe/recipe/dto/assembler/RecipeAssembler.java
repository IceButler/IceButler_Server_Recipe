package smile.iceBulterrecipe.recipe.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.recipe.dto.request.PostRecipeReq;
import smile.iceBulterrecipe.recipe.dto.response.MyRecipeRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeCategory;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

@Component
@RequiredArgsConstructor
public class RecipeAssembler {

    public Recipe toEntity(PostRecipeReq recipeReq, User user) {
        return Recipe.builder()
                .recipeName(recipeReq.getRecipeName())
                .recipeImgKey(recipeReq.getRecipeImgKey())
                .recipeCategory(RecipeCategory.getFoodCategoryByName(recipeReq.getRecipeCategory()))
                .quantity(recipeReq.getQuantity())
                .leadTime(recipeReq.getLeadTime())
                .user(user)
                .build();

    }

    public Page<RecipeRes> toDtoList(Page<Recipe> recipeLists) {
        return recipeLists.map(r ->
                RecipeRes.builder()
                        .recipeIdx(r.getRecipeIdx())
                        .recipeName(r.getRecipeName())
                        .recipeImgUrl(AwsS3ImageUrlUtils.toUrl(r.getRecipeImgKey()))
                        .recipeCategory(r.getRecipeCategory().getCategory())
                        .build());
    }

    public Page<MyRecipeRes> toDtoMyRecipeList(Page<Recipe> myRecipeList) {
        return myRecipeList.map(r ->
                MyRecipeRes.builder()
                        .recipeIdx(r.getRecipeIdx())
                        .recipeImgUrl(AwsS3ImageUrlUtils.toUrl(r.getRecipeImgKey()))
                        .recipeName(r.getRecipeName())
                        .recipeCategory(r.getRecipeCategory().getCategory())
                        .build());
    }

    public RecipeReport toReportEntity(Recipe recipe, User user, Reason recipeReason) {
        return RecipeReport.builder()
                .user(user)
                .recipe(recipe)
                .reason(recipeReason)
                .build();
    }


}
