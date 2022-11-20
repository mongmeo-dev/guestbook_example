package dev.mongmeo.guestbook.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {

  private final HttpStatus responseStatus;

  public ServerException(String message) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  public ServerException(HttpStatus responseStatus, String message) {
    super(message);
    this.responseStatus = responseStatus;
  }
}
