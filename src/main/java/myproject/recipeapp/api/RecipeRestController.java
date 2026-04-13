package myproject.recipeapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myproject.recipeapp.domain.Recipe;
import myproject.recipeapp.repository.RecipeRepository;

@RestController
@RequestMapping("/api/recipes")
public class RecipeRestController {

  private final RecipeRepository recipeRepository;

  // Construction Injection
  public RecipeRestController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping
  public Iterable<Recipe> findAllRecipes() {
    return recipeRepository.findAll();
  }

  @GetMapping("/{id}")
  public Recipe findById(@PathVariable Long id) {
    return recipeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reseptiä ei löydy"));
}

  @PostMapping
  public Recipe saveRecipe (@RequestBody Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  @PutMapping("/{id}")
  public Recipe saveEditedRecipe(@RequestBody Recipe editedRecipe, @PathVariable Long id) {
    editedRecipe.setId(id);
    return recipeRepository.save(editedRecipe);
  }

  @DeleteMapping("/{id}")
  public Iterable<Recipe> deleteRecipe (@PathVariable Long id) {
    System.out.println("Poistettavan reseptin id " + id);
    recipeRepository.deleteById(id);
    return recipeRepository.findAll();
  }
}
