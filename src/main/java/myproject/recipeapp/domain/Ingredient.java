package myproject.recipeapp.domain;

import java.util.ArrayList;

//import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Ingredient {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@OneToMany(mappedBy = "ingredient")
private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

public Ingredient() {
}

public Ingredient(String name) {
  this.name = name;
}

public Long getId() {
  return id;
}

public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public List<RecipeIngredient> getRecipeIngredients() {
  return recipeIngredients;
}

public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
  this.recipeIngredients = recipeIngredients;
}

@Override
public String toString() {
  return "Ingredient [id=" + id + ", name=" + name + "]";
}

}