package com.example.UserRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

  @Autowired
  private UserRepository repo;

  /**
   *  Returns index.html.
   * @return String
   */
  @GetMapping("")
  public String viewHomePage(){
    return "index";
  }

  /**
   * Returns signup_form.html
   * @param model Model
   * @return String
   */
  @GetMapping("/register")
  public String showSignUpForm(Model model){
    model.addAttribute("user", new User());
    return "signup_form";
  }

  /**
   * Returns register_success.html
   * @param user User
   * @return String
   */
  @PostMapping("/process_register")
  public String processRegistration(User user){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getMobileNo());
    user.setMobileNo(encodedPassword);
    repo.save(user);

    return "register_success";
  }

  /**
   * Returns list of registered users.
   * @param model Model
   * @return String
   */
  @GetMapping("/list_users")
  public String viewUsersList(Model model){
    List<User> listUsers = repo.findAll();
    model.addAttribute("listUsers",listUsers);
    return "users";
  }
}