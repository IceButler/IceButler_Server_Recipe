package smile.iceBulterrecipe.recipe.repository.recipe;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

import static smile.iceBulterrecipe.recipe.entity.QRecipe.recipe;
import static smile.iceBulterrecipe.recipe.entity.QRecipeFood.recipeFood;


@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Recipe> findByFoodNameContainingAndIsEnableHavingRecipe(String keyword, Pageable pageable) {
        List<Recipe> fetch = this.jpaQueryFactory.selectFrom(recipe)
                .leftJoin(recipeFood).on(recipe.eq(recipeFood.recipe))
                .where(recipe.isEnable.eq(true)
                        .and(recipeFood.isEnable.eq(true))
                        .and(recipeFood.food.foodName.contains(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(recipe)
                .fetch();


        JPAQuery<Long> count = jpaQueryFactory.select(recipe.count())
                .from(recipe)
                .leftJoin(recipeFood).on(recipe.eq(recipeFood.recipe))
                .where(recipe.isEnable.eq(true)
                        .and(recipeFood.isEnable.eq(true))
                        .and(recipeFood.food.foodName.contains(keyword)))
                .groupBy(recipe);

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }
}
