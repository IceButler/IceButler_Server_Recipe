package smile.iceBulterrecipe.admin.dto.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import smile.iceBulterrecipe.admin.dto.response.GetRecipeReportRes;
import smile.iceBulterrecipe.recipe.entity.RecipeReport;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class AdminAssembler {
    public Page<GetRecipeReportRes> toAdminReportEntity(Page<RecipeReport> recipeReports){
        AtomicInteger rowNumber = new AtomicInteger(recipeReports.getNumber() * recipeReports.getSize() + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 출력 형식 지정

        return recipeReports.map(r ->
                GetRecipeReportRes.builder()
                        .recipeReportIdx((long) rowNumber.getAndIncrement())
                        .recipeName(r.getRecipe().getRecipeName())
                        .author(r.getRecipe().getUser().getNickname())
                        .reason(r.getReason())
                        .reporter(r.getUser().getNickname())
                        .reportDate(r.getCreatedAt().format(formatter))
                        .build());
    }
}
