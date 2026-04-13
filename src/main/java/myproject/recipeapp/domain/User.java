package myproject.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "app_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

  @ManyToMany
  @JoinTable(
    name = "favourites",
    joinColumns = @JoinColumn(name = "app_user_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id")
  )

  private List<Recipe> favouriteRecipes = new ArrayList<>();

  public User() {
  }

  public User(String username, String passwordHash, Role role) {
    //super();
    this.username = username;
    this.passwordHash = passwordHash;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
     this.id = id;
   }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Recipe> getFavouriteRecipes() {
    return favouriteRecipes;
  }

  public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
    this.favouriteRecipes = favouriteRecipes;
  }

  

}
