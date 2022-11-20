package dev.mongmeo.guestbook.web.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class ViewLoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Object logonUserId = request.getSession().getAttribute("logonUserId");

    if (Objects.isNull(logonUserId)) {
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }
}
