package smile.iceBulterrecipe.recipe.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.recipe.FoodCategory;
import smile.iceBulterrecipe.user.entity.User;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE recipe SET status = 'inactive', last_modified_date = current_timestamp WHERE recipe_idx = ?")
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recipeIdx;
    private String recipeName;
    private String recipeImgKey;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;
    private Long leadTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;
}
