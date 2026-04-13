package myproject.recipeapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import myproject.recipeapp.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
  
    Optional<Ingredient> findByNameIgnoreCase(String name);
}
