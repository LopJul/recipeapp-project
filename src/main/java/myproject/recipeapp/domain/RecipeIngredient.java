package myproject.recipeapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RecipeIngredient {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Ingredient ingredient;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public Recipe getRecipe() {
      return recipe;
    }
    public void setRecipe(Recipe recipe) {
      this.recipe = recipe;
    }
    public Ingredient getIngredient() {
      return ingredient;
    }
    public void setIngredient(Ingredient ingredient) {
      this.ingredient = ingredient;
    }
    @Override
    public String toString() {
      return "RecipeIngredient [id=" + id + ", recipe=" + recipe + ", ingredient=" + ingredient + "]";
    }

}
