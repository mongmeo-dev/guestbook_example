package dev.mongmeo.guestbook.config;

import dev.mongmeo.guestbook.web.interceptor.ApiLoginCheckInterceptor;
import dev.mongmeo.guestbook.web.interceptor.ViewLoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ApiLoginCheckInterceptor())
        .addPathPatterns("/api/**")
        .excludePathPatterns("/api/login", "/api/users");
    registry.addInterceptor(new ViewLoginCheckInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/api/**", "/login", "/join", "/error", "/js/**");
  }
}
