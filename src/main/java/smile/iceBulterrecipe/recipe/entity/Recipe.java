package smile.iceBulterrecipe.recipe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.recipe.FoodCategory;
import smile.iceBulterrecipe.user.entity.User;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recipeIdx;
    private String recipeName;
    private String recipeImg;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;
    private Integer leadTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;
}
