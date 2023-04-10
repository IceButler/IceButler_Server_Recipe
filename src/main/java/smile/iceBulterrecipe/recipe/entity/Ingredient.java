package smile.iceBulterrecipe.recipe.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import smile.iceBulterrecipe.recipe.FoodCategory;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Ingredient {
    @Id // id 직접 할당
    @Column(nullable = false)
    private Long ingredientIdx;
    private String ingredientName;
    private String ingredientIconName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory foodCategory;
}
