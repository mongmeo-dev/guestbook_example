package dev.mongmeo.guestbook.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestbookViewController {

  @GetMapping("/create")
  public String guestbookCreateForm() {
    return "guestbookCreateForm";
  }

  @GetMapping
  public String guestbookListView() {
    return "guestbookList";
  }

  @GetMapping("/{id}")
  public String guestbookDetailView() {
    return "guestbookDetail";
  }

  @GetMapping("/{id}/delete")
  public String guestbookDeleteForm() {
    return "guestbookDeleteForm";
  }
}
