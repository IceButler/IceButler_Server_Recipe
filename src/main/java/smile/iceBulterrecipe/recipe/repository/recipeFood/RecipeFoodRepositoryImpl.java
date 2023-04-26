package smile.iceBulterrecipe.recipe.repository.recipeFood;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.dto.response.BookMarkRecipeListRes;
import smile.iceBulterrecipe.recipe.dto.response.BookmarkRecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

import static smile.iceBulterrecipe.recipe.entity.QRecipeFood.recipeFood;


@RequiredArgsConstructor
public class RecipeFoodRepositoryImpl implements RecipeFoodCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BookMarkRecipeListRes getBookmarkRecipes(List<Recipe> bookmarkRecipeList, List<Long> foodIdxes) {
        return new BookMarkRecipeListRes(bookmarkRecipeList.stream()
                .map(recipe -> BookmarkRecipeRes.toDto(recipe, getPercentageOfFood(recipe, foodIdxes)))
                .collect(Collectors.toList()));
    }

    // 레시피 내 보유한 음식 백분율 계산
    private long getPercentageOfFood(Recipe recipe, List<Long> foodIdxes) {
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
        return recipeFridgeFoodNum / recipeFoodNum * 100;
    }

}
