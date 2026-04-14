package myproject.recipeapp;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import myproject.recipeapp.domain.Recipe;
// import myproject.recipeapp.domain.Role;
// import myproject.recipeapp.domain.User;
// import myproject.recipeapp.repository.RecipeRepository;
// import myproject.recipeapp.repository.UserRepository;

@SpringBootApplication
public class RecipeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeappApplication.class, args);
	}

// @Bean 
// public CommandLineRunner recipeDemo(RecipeRepository recipeRepository, UserRepository userRepository) {
// 		return (args) -> {

// 							//Check if there is already user data in the database
// 			if (userRepository.count() == 0) {

//  			User user1 = new User("user", "$2a$10$xsSglTlHmiXBxLBh8lmR2uSIxj/B05AcHKcmWyNHkTZYs/.fEMiG6", Role.USER);
//  			User user2 = new User("admin", "$2a$10$kgsnf8QVsZmNe0jFm0mwmOc.EkZnq6yELdecrgC8Fw7Tz2gCcVrei", Role.ADMIN);
//  			userRepository.save(user1);
//  			userRepository.save(user2);
// 			}

// 			 User admin = userRepository.findByUsername("admin");


// 			recipeRepository.save(new Recipe("Pasta", "Keitä pasta", admin));
//     	recipeRepository.save(new Recipe("Pizza", "Paista pizza", admin));

//  		};
//  }

}
