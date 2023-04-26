package smile.iceBulterrecipe.recipe.repository.recipeFood;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RecipeFoodRepositoryImpl implements RecipeFoodCustom {
    private final JPAQueryFactory jpaQueryFactory;

}
