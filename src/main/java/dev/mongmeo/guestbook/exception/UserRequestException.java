package dev.mongmeo.guestbook.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserRequestException extends RuntimeException {

  private final HttpStatus responseStatus;

  public UserRequestException(String message) {
    this(HttpStatus.BAD_REQUEST, message);
  }

  public UserRequestException(HttpStatus responseStatus, String message) {
    super(message);
    this.responseStatus = responseStatus;
  }
}
