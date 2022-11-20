package dev.mongmeo.guestbook.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

  @GetMapping("/login")
  public String loginForm() {
    return "loginForm";
  }

  @GetMapping("/logout")
  public String logoutForm() {
    return "logoutForm";
  }
}
