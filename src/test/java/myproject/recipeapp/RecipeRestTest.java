package myproject.recipeapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@org.springframework.test.context.ActiveProfiles("test") 
public class RecipeRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRecipes() throws Exception {
      mockMvc.perform(get("/api/recipes")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void testCreateRecipe() throws Exception {
    String json = """
        {
          "title": "TestiResepti",
          "instructions": "TestiOhje"
        }
        """;

      mockMvc.perform(post("/api/recipes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());
}

}