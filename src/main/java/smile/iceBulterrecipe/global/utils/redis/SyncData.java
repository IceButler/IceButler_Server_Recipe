package smile.iceBulterrecipe.global.utils.redis;

import lombok.Data;

@Data
public class SyncData {
    private boolean recipeService;
    private boolean mainService;
    public boolean allTure() {
        return this.recipeService && this.mainService;
    }
    public SyncData changeTrue() {
        this.recipeService = true;
        return this;
    }

    public SyncData() {
        this.recipeService = true;
        this.mainService = false;
    }
}
