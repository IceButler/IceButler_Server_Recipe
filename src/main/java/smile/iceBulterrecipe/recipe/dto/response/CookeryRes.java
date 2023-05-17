package smile.iceBulterrecipe.recipe.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import smile.iceBulterrecipe.global.utils.AwsS3ImageUrlUtils;

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

  public static CookeryRes toDto(String description, String cookeryImgKey, Long nextIdx) {
    return CookeryRes.builder()
            .description(description)
            .cookeryImgUrl(AwsS3ImageUrlUtils.toUrl(cookeryImgKey))
            .nextIdx(nextIdx)
            .build();
  }

}
