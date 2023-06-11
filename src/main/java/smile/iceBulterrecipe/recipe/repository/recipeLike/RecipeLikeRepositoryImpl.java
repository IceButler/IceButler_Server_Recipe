package smile.iceBulterrecipe.recipe.repository.recipeLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import smile.iceBulterrecipe.recipe.dto.response.RecipeRes;
import smile.iceBulterrecipe.recipe.entity.Recipe;
import smile.iceBulterrecipe.recipe.entity.RecipeLike;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<RecipeRes> getPopularRecipe(Pageable pageable) {

        List<Recipe> fetch = jpaQueryFactory
                .selectFrom(recipe)
                .distinct()
                .leftJoin(recipeLike)
                    .on(recipe.recipeIdx.eq(recipeLike.recipe.recipeIdx))
                .where(recipe.isEnable.eq(true))
                .fetch();

        List<RecipeRes> collect = fetch.stream()
                .sorted((a, b) -> b.getRecipeLikes().size() - a.getRecipeLikes().size())
                .map(RecipeRes::toDto)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()),  collect.size());
        return new PageImpl<>(collect.subList(start, end), pageable, collect.size());

    }


    @Override
    public List<RecipeLike> getBookmarkRecipe(User user, boolean status) {
        return jpaQueryFactory.select(recipeLike)
                .from(recipeLike)
                .where(recipeLike.user.eq(user)
                .and(recipeLike.isEnable.eq(status))
                .and(recipe.isEnable.eq(status)))
                .orderBy(recipeLike.updateAt.desc())
                .fetch();
    }

}
