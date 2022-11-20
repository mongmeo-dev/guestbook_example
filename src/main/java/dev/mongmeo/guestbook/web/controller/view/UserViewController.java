package dev.mongmeo.guestbook.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

  @GetMapping("/join")
  public String joinForm() {
    return "joinForm";
  }

  @GetMapping("/user/{userId}/delete")
  public String userDeleteForm() {
    return "userDeleteForm";
  }
}
