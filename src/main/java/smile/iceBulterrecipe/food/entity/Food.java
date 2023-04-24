package smile.iceBulterrecipe.food.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Food {
    @Id// id 직접 할당
    @Column(nullable = false)
    private Long foodIdx;
    private String foodName;
    private String foodImgKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory foodCategory;

    @Builder
    public Food(Long foodIdx, String foodName, String foodImgKey, FoodCategory foodCategory) {
        this.foodIdx = foodIdx;
        this.foodName = foodName;
        this.foodImgKey = foodImgKey;
        this.foodCategory = foodCategory;
    }
}
