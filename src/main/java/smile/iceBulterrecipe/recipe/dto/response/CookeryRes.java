package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CookeryRes {
  private Long nextIdx;
  private String description;
  private String cookeryImgUrl;

  @Builder
  public CookeryRes(String description, String cookeryImgUrl, Long nextIdx) {
    this.description = description;
    this.cookeryImgUrl = cookeryImgUrl;
    this.nextIdx = nextIdx;
  }

  public static CookeryRes toDto(String description, String cookeryImgUrl, Long nextIdx) {
    return CookeryRes.builder()
            .description(description)
            .cookeryImgUrl(cookeryImgUrl)
            .nextIdx(nextIdx)
            .build();
  }

}
