package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CookeryRes {
  private String description;
  private String cookeryImgUrl;

  @Builder
  public CookeryRes(String description, String cookeryImgUrl) {
    this.description = description;
    this.cookeryImgUrl = cookeryImgUrl;
  }

  public static CookeryRes toDto(String description, String cookeryImgUrl) {
    return CookeryRes.builder()
            .description(description)
            .cookeryImgUrl(cookeryImgUrl)
            .build();
  }
}
