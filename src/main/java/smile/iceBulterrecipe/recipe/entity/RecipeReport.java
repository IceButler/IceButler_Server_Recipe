package smile.iceBulterrecipe.recipe.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import smile.iceBulterrecipe.global.BaseEntity;
import smile.iceBulterrecipe.recipe.Reason;
import smile.iceBulterrecipe.user.entity.User;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE recipe_report SET is_enable = false, update_at = current_timestamp WHERE recipe_report_idx = ?")
public class RecipeReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long recipeReportIdx;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipeIdx")
    private Recipe recipe;

    @Enumerated(EnumType.STRING)
    private Reason reason;
}
