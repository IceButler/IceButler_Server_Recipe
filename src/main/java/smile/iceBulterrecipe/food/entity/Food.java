package smile.iceBulterrecipe.food.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import smile.iceBulterrecipe.food.dto.request.FoodReq;
import smile.iceBulterrecipe.global.BaseEntity;

import java.util.UUID;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Food extends BaseEntity {
    @Id// id 직접 할당
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long foodIdx;

    private String foodName;
    private String foodImgKey;

    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory foodCategory;

    @Builder
    public Food(String foodName, String foodImgKey, FoodCategory foodCategory, UUID uuid) {
        this.foodName = foodName;
        this.foodImgKey = foodImgKey;
        this.foodCategory = foodCategory;
        this.uuid = uuid;
    }

    public void deleteFood() {
        this.setIsEnable(false);
    }

    public void toUpdateFoodInfo(FoodReq foodReq) {
        this.foodCategory = FoodCategory.getFoodCategory(foodReq.getFoodCategory());
        this.foodName = foodReq.getFoodName();
        this.foodImgKey = foodReq.getFoodImgKey();
    }
}
