package dev.mongmeo.guestbook.web.exceptionhandler;

import dev.mongmeo.guestbook.exception.ServerException;
import dev.mongmeo.guestbook.exception.UserRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServerException.class)
  public ResponseEntity<String> handleServerError(ServerException exception) {
    log.error("server exception occurred : ", exception);
    return ResponseEntity.status(exception.getResponseStatus()).body(exception.getMessage());
  }

  @ExceptionHandler(UserRequestException.class)
  public ResponseEntity<String> handleUserRequestException(UserRequestException exception) {
    log.error("user request exception occurred : ", exception);
    return ResponseEntity.status(exception.getResponseStatus()).body(exception.getMessage());
  }
}
