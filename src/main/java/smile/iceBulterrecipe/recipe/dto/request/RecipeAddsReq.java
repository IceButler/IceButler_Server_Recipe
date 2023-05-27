package smile.iceBulterrecipe.recipe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeAddsReq {
    private List<RecipeAddReq> fridgeFoods;

}
