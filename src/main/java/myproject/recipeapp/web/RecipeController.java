package myproject.recipeapp.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import myproject.recipeapp.domain.Ingredient;
import myproject.recipeapp.domain.Recipe;
import myproject.recipeapp.domain.RecipeIngredient;
import myproject.recipeapp.domain.User;
import myproject.recipeapp.repository.IngredientRepository;
import myproject.recipeapp.repository.RecipeRepository;
import myproject.recipeapp.repository.UserRepository;

@Controller
public class RecipeController {

  private final RecipeRepository recipeRepository;
  private final UserRepository userRepository;
  private final IngredientRepository ingredientRepository;

  public RecipeController(RecipeRepository recipeRepository, UserRepository userRepository, IngredientRepository ingredientRepository) {
    this.recipeRepository = recipeRepository;
    this.userRepository = userRepository;
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

   @GetMapping({"/", "/recipes"})
    public String getRecipes(Model model) {
    model.addAttribute("recipes", recipeRepository.findAll());
     return "recipes";
   }

   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   @GetMapping("/addRecipe")
    public String addRecipe(Model model){
      model.addAttribute("recipe", new Recipe());
      return "addRecipe";
    }

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PostMapping("/save")
public String save(@Valid Recipe recipe,
                   BindingResult bindingResult,
                   Model model,
                   Principal principal) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("recipe", recipe);
        return "addRecipe";
    }

    User user = userRepository.findByUsername(principal.getName());
    recipe.setUser(user);

    List<RecipeIngredient> links = new ArrayList<>();

    if (recipe.getRecipeIngredients() != null) {
        for (RecipeIngredient ri : recipe.getRecipeIngredients()) {

            if (ri.getIngredient() == null ||
                ri.getIngredient().getName() == null ||
                ri.getIngredient().getName().trim().isEmpty()) {
                continue;
            }

            String name = ri.getIngredient().getName().trim();

            Ingredient ingredient = ingredientRepository
                    .findByNameIgnoreCase(name)
                    .orElseGet(() -> {
                        Ingredient newIng = new Ingredient();
                        newIng.setName(name);
                        return ingredientRepository.save(newIng);
                    });

            RecipeIngredient link = new RecipeIngredient();
            link.setRecipe(recipe);
            link.setIngredient(ingredient);

            links.add(link);
        }
    }

    recipe.setRecipeIngredients(links);
    recipeRepository.save(recipe);

    return "redirect:/recipes";
}


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/deleteRecipe/{id}")
    public String deleteRecipe(@PathVariable Long id) {

    Recipe recipe = recipeRepository.findById(id)
            .orElseThrow();

    // Poista resepti kaikilta käyttäjiltä suosikeita 
    for (User user : userRepository.findAll()) {
        user.getFavouriteRecipes().remove(recipe);
    }

    recipe.getRecipeIngredients().clear();

    userRepository.saveAll(userRepository.findAll());

    recipeRepository.delete(recipe);

    return "redirect:/recipes";
}

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/editRecipe/{id}")
      public String editRecipe(@PathVariable("id") Long recipeId, Model model){
        model.addAttribute("recipe", recipeRepository.findById(recipeId).orElseThrow());
          return "editRecipe";
      }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  
    @PostMapping("/update")
        public String update(@Valid Recipe recipe,
                     BindingResult bindingResult,
                     Model model) {

        if (bindingResult.hasErrors()) {
        model.addAttribute("recipe", recipe);
            return "editRecipe";
    }

    Recipe existing = recipeRepository.findById(recipe.getId())
            .orElseThrow();

    existing.setTitle(recipe.getTitle());
    existing.setInstructions(recipe.getInstructions());

    // clear correctly (orphanRemoval toimii)
    existing.getRecipeIngredients().clear();

    List<RecipeIngredient> links = new ArrayList<>();

    if (recipe.getRecipeIngredients() != null) {
        for (RecipeIngredient ri : recipe.getRecipeIngredients()) {

            if (ri.getIngredient() == null ||
                ri.getIngredient().getName() == null ||
                ri.getIngredient().getName().trim().isEmpty()) {
                continue;
            }

            String name = ri.getIngredient().getName().trim();

            Ingredient ingredient = ingredientRepository
                    .findByNameIgnoreCase(name)
                    .orElseGet(() -> {
                        Ingredient newIng = new Ingredient();
                        newIng.setName(name);
                        return ingredientRepository.save(newIng);
                    });

            RecipeIngredient link = new RecipeIngredient();
            link.setRecipe(existing);
            link.setIngredient(ingredient);

            links.add(link);
        }
    }

    existing.getRecipeIngredients().addAll(links);

    recipeRepository.save(existing);

    return "redirect:/recipes";
}


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/recipes/{id}/likeRecipe")
    public String likeRecipe(@PathVariable Long id, Principal principal) {

      if (principal == null) {
        return "redirect:/login";
    }

    User user = userRepository.findByUsername(principal.getName());

    Recipe recipe = recipeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid recipe id"));

    if (!user.getFavouriteRecipes().contains(recipe)) {
        user.getFavouriteRecipes().add(recipe);
    }

    userRepository.save(user);

    return "redirect:/favourites";
}

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/favourites")
    public String getFavourites(Model model, Principal principal) {

    User user = userRepository.findByUsername(principal.getName()); // testikäyttäjä

    model.addAttribute("favourites", user.getFavouriteRecipes());

    return "favourites";
}

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/recipes/{id}/unlike")
    public String unlikeRecipe(@PathVariable Long id, Principal principal) {

    User user = userRepository.findByUsername(principal.getName());
    Recipe recipe = recipeRepository.findById(id).orElseThrow();

    user.getFavouriteRecipes().remove(recipe);

    userRepository.save(user);

    return "redirect:/favourites";
} 
}
