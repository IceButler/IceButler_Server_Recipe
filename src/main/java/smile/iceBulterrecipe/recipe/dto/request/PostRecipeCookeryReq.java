package smile.iceBulterrecipe.recipe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRecipeCookeryReq {
    private String cookeryImgKey;
    private String cookeryDescription;
}
