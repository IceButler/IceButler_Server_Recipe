package smile.iceBulterrecipe.recipe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRecipeFoodReq {
    private String foodName;
    private String foodDetail;

}
