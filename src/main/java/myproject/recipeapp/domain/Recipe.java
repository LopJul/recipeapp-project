package myproject.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Otsikko ei voi olla tyhjä.")
  @Size(min = 2, max = 100, message = "Otsikon pitää olla vähintään 2 ja enintään 100 merkkiä.")
  private String title;
  @NotEmpty(message = "Ohje-kenttä ei voi olla tyhjä.")
  @Size(min = 5, max = 1000, message = "Ohjeen pitää olla vähintään 5 ja enintään 1000 merkkiä.")
  private String instructions;

  @ManyToOne
  @JoinColumn(name = "app_user_id")
  @JsonIgnore
  private User user;

  @ManyToMany(mappedBy = "favouriteRecipes")
  @JsonIgnore
  private List<User> usersWhoFavorited = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

  public Recipe() {
  }

  public Recipe(String title, String instructions, User user) {
    this.title = title;
    this.instructions = instructions;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
  this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<RecipeIngredient> getRecipeIngredients() {
    return recipeIngredients;
  }

  public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
    this.recipeIngredients = recipeIngredients;
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", title=" + title + ", instructions=" + instructions + "]";
  }

}
