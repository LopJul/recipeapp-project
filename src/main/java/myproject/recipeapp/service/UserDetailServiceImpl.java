package myproject.recipeapp.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myproject.recipeapp.domain.User;
import myproject.recipeapp.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  
  private final UserRepository repository;

  // Constructor Injection
  public UserDetailServiceImpl(UserRepository userRepository) {
    this.repository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList("ROLE_" + curruser.getRole().name()));

            
        return user;

        
    }   
  
}
