package smile.iceBulterrecipe.recipe.repository.recipeLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

import static smile.iceBulterrecipe.recipe.entity.QRecipe.recipe;
import static smile.iceBulterrecipe.recipe.entity.QRecipeLike.recipeLike;


@RequiredArgsConstructor
public class RecipeLikeRepositoryImpl implements RecipeLikeCustom{
    private final JPAQueryFactory jpaQueryFactory;

    // 인기 레시피 불러오기
    @Override
    public List<Recipe> getPopularRecipe() {
        return jpaQueryFactory.select(recipe)
                .from(recipeLike)
                .where(recipeLike.isEnable.eq(true))
                .groupBy(recipeLike.recipe)
                .orderBy(recipeLike.recipe.count().desc())
                .fetch();
    }
}
