package myproject.recipeapp.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(Exception.class)
  public String handleError(Model model) {
    model.addAttribute("message", "Hupsis! Jokin meni pieleen.");
    return "errorPage";
  }
}
