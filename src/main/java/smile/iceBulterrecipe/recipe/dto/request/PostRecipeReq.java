package smile.iceBulterrecipe.recipe.dto.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;
import smile.iceBulterrecipe.recipe.entity.Recipe;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRecipeReq {
    private String recipeName;
    private String recipeImgKey;
    private String recipeCategory;
    private Long leadTime;
    private Integer quantity; // 하나는 왜 Integer 하나는 왜 Long?
    private List<PostRecipeFoodReq> foodList;
    private List<PostRecipeCookeryReq> cookeryList;
}
