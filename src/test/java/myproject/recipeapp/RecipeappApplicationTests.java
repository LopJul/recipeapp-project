package myproject.recipeapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import myproject.recipeapp.api.RecipeRestController;
import myproject.recipeapp.web.RecipeController;

@SpringBootTest
class RecipeappApplicationTests {

	@Autowired
	private RecipeController recipeController;

	@Autowired
	private RecipeRestController recipeRestController;

	@Test
	void contextLoads() {
	}

	@Test
	public void recipeControllerWorks() throws Exception {
		assertThat (recipeController).isNotNull();
	}

	@Test
	public void recipeRestControllerWorks() throws Exception {
		assertThat (recipeRestController).isNotNull();
		}

}
