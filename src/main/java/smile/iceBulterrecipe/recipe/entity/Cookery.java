package smile.iceBulterrecipe.recipe.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import smile.iceBulterrecipe.global.BaseEntity;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE cookery SET is_enable = false, update_at = current_timestamp WHERE cookery_idx = ?")
public class Cookery extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long cookeryIdx;

    private Long nextIdx;
    private String cookeryImgKey;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipeIdx")
    private Recipe recipe;
}
