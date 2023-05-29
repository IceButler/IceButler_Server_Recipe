package smile.iceBulterrecipe.admin.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import smile.iceBulterrecipe.admin.dto.response.UserResponse;
import smile.iceBulterrecipe.recipe.entity.QRecipeReport;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;
import smile.iceBulterrecipe.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static smile.iceBulterrecipe.recipe.entity.QRecipeReport.recipeReport;
import static smile.iceBulterrecipe.user.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class AdminRepositoryQuerydslImpl implements  AdminRepositoryQuerydsl{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserResponse> findAllByNicknameAndActive(
            Pageable pageable,
            String nickname,
            boolean active)
    {
        List<User> result = queryFactory
                .selectFrom(user)
                .where(
                        nicknameLike(nickname),
                        user.isEnable.eq(active)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(user.userIdx.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(user.count())
                .from(user)
                .where(
                        nicknameLike(nickname),
                        user.isEnable.eq(active)
                );

        List<UserResponse> userResponses = result.stream().map(UserResponse::toDto).collect(Collectors.toList());

        List<RecipeReport> reports = queryFactory
                .selectFrom(recipeReport)
                .where(
                        recipeReport.user.userIdx.in(userResponses.stream().map(UserResponse::getUserIdx).collect(Collectors.toList()))
                )
                .fetch();

        for (UserResponse userResponse : userResponses)
            for (RecipeReport report : reports)
                if(userResponse.getUserIdx().equals(report.getUser().getUserIdx())) userResponse.upReportCount();

        return PageableExecutionUtils.getPage(userResponses, pageable, countQuery::fetchOne);
    }


    private BooleanExpression nicknameLike(String nickname)
    {
        return hasText(nickname) ? user.nickname.contains(nickname) : null;
    }
}
