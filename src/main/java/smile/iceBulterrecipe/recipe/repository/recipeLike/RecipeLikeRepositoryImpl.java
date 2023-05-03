package smile.iceBulterrecipe.recipe.repository.recipeLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;

import static smile.iceBulterrecipe.recipe.entity.QRecipe.recipe;
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
    public List<Recipe> getPopularRecipe() {
        return jpaQueryFactory.selectFrom(recipe)
                .leftJoin(recipeLike).on(recipe.eq(recipeLike.recipe))
                .where(recipe.isEnable.eq(true))
                .groupBy(recipe)
                .orderBy(recipeLike.recipe.isEnable.count().desc())
                .fetch();
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
