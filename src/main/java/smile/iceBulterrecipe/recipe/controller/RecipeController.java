package smile.iceBulterrecipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/recipes")
@RestController
@RequiredArgsConstructor
public class RecipeController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }
}
