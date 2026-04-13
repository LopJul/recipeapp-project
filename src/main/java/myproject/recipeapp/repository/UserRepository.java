package myproject.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;

import myproject.recipeapp.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}

