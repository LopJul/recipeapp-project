package myproject.recipeapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import myproject.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

    List<Recipe> findByTitle(String title);
}
