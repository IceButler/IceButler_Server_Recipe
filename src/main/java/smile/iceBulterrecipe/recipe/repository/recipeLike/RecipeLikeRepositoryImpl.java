package smile.iceBulterrecipe.recipe.repository.recipeLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

import static smile.iceBulterrecipe.recipe.entity.QRecipe.recipe;
import static smile.iceBulterrecipe.recipe.entity.QRecipeFood.recipeFood;
import static smile.iceBulterrecipe.recipe.entity.QRecipeLike.recipeLike;

@RequiredArgsConstructor
public class RecipeLikeRepositoryImpl implements RecipeLikeCustom{
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * select recipe.recipe_idx, ifnull(sum(rl.is_enable = 1), 0)
     * from recipe
     * left join recipe_like rl on recipe.recipe_idx = rl.recipe_idx
     * where recipe.is_enable = 1
     * group by recipe.recipe_idx
     * order by sum(rl.is_enable) desc
     */

    // 인기 레시피 불러오기
    @Override
    public Page<RecipeRes> getPopularRecipe(Pageable pageable, List<Long> foodIdxes) {

        List<Recipe> fetch = jpaQueryFactory
                .selectFrom(recipe)
                .distinct()
                .leftJoin(recipeLike)
                    .on(recipe.recipeIdx.eq(recipeLike.recipe.recipeIdx))
                .where(recipe.isEnable.eq(true))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetch();

        List<RecipeRes> collect = fetch.stream()
                .filter(r -> getPercentageOfFood(r.getRecipeIdx(), foodIdxes) >= 50)
                .sorted((a, b) -> b.getRecipeLikes().size() - a.getRecipeLikes().size())
                .map(RecipeRes::toDto)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()),  collect.size());
//        return PageableExecutionUtils.getPage
        return new PageImpl<>(collect.subList(start, end), pageable, collect.size());

    }

    public Integer getPercentageOfFood(Long recipeIdx, List<Long> foodIdxes) {
        // 레시피 food 중 냉장고에 보유하고 있는 food 수
        long recipeFridgeFoodNum = jpaQueryFactory.selectFrom(recipeFood)
                .where(recipeFood.recipe.recipeIdx.eq(recipeIdx)
                        .and(recipeFood.food.foodIdx.in(foodIdxes)))
                .stream().count();

        // 레시피 총 food 수
        long recipeFoodNum = jpaQueryFactory.selectFrom(recipeFood)
                .where(recipeFood.recipe.recipeIdx.eq(recipeIdx))
                .stream().count();

        // 나누는 수가 0이면 오류로 0 반환
        if(recipeFoodNum==0) return 0;
        double percentageOfFood = recipeFridgeFoodNum / (double) recipeFoodNum * 100;
        return (int)Math.round(percentageOfFood);
    }


    @Override
    public List<Recipe> getBookmarkRecipe(User user, boolean status) {
        return jpaQueryFactory.select(recipe)
                .from(recipeLike)
                .where(recipeLike.user.eq(user)
                .and(recipeLike.isEnable.eq(status)))
                .groupBy(recipeLike.recipe)
                .orderBy(recipeLike.updateAt.desc())
                .fetch();
    }
}
