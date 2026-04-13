package myproject.recipeapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import myproject.recipeapp.domain.Recipe;
import myproject.recipeapp.repository.RecipeRepository;

@DataJpaTest
@ActiveProfiles("test")
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void saveNewRecipe() {
        Recipe recipe = new Recipe("Pizza", "Paista uunissa", null);

        recipeRepository.save(recipe);

        assertThat(recipe.getId()).isNotNull();
    }

    @Test
    public void deleteRecipe() {
        List<Recipe> recipes = recipeRepository.findByTitle("Lohisalaatti");
        Recipe recipe = recipes.get(0);
        recipeRepository.delete(recipe);
        List<Recipe> newRecipes = recipeRepository.findByTitle("Lohisalaatti");
        assertThat(newRecipes).hasSize(0);
}
}