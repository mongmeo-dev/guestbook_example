package dev.mongmeo.guestbook.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class CommentViewController {

  @GetMapping("/{guestbookId}/comment/{commentId}/delete")
  public String guestbookDeleteForm() {
    return "commentDeleteForm";
  }
}
