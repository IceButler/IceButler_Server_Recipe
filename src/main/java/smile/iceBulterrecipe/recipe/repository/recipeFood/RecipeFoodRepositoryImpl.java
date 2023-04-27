package smile.iceBulterrecipe.recipe.repository.recipeFood;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.dto.response.RecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

import static smile.iceBulterrecipe.recipe.entity.QRecipe.recipe;
import static smile.iceBulterrecipe.recipe.entity.QRecipeFood.recipeFood;


@RequiredArgsConstructor
public class RecipeFoodRepositoryImpl implements RecipeFoodCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public RecipeListRes getBookmarkRecipes(List<Recipe> bookmarkRecipeList, List<Long> foodIdxes) {
        return new RecipeListRes(bookmarkRecipeList.stream()
                .map(recipe -> RecipeRes.toDto(recipe, getPercentageOfFood(recipe, foodIdxes)))
                .collect(Collectors.toList()));
    }

    // 레시피 내 보유한 음식 백분율 계산
    @Override
    public Integer getPercentageOfFood(Recipe recipe, List<Long> foodIdxes) {
        // 레시피 food 중 냉장고에 보유하고 있는 food 수
        long recipeFridgeFoodNum = jpaQueryFactory.selectFrom(recipeFood)
                .where(recipeFood.recipe.eq(recipe)
                        .and(recipeFood.food.foodIdx.in(foodIdxes)))
                .stream().count();

        // 레시피 총 food 수
        long recipeFoodNum = jpaQueryFactory.selectFrom(recipeFood)
                .where(recipeFood.recipe.eq(recipe))
                .stream().count();

        // 나누는 수가 0이면 오류로 0 반환
        if(recipeFoodNum==0) return 0;
        double percentageOfFood = recipeFridgeFoodNum / (double) recipeFoodNum * 100;
        return (int)Math.round(percentageOfFood);
    }

    // todo: 정렬 필요 아직 order by에 무엇을 넣어야 하는지 모르겠음.
    @Override
    public List<Recipe> getRecipeByFridgeFoodList(List<Long> foodIdxes) {
        return jpaQueryFactory.selectFrom(recipe)
                .leftJoin(recipeFood).on(recipe.eq(recipeFood.recipe))
                .where(recipeFood.recipe.eq(recipe).and(recipeFood.isEnable.eq(true))
                        .and(recipeFood.food.foodIdx.in(foodIdxes)))
                .groupBy(recipeFood.recipe)
                .fetch();
    }


}
