package com.example.hostelapi.controller.exceptions;

import com.example.hostelapi.service.exceptions.InvalidDataException;
import com.example.hostelapi.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para tratamento global de exceções lançadas por controladores.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Trata exceções do tipo ObjectNotFoundException.
   *
   * @param e       A exceção ObjectNotFoundException lançada.
   * @param request A requisição HTTP onde a exceção ocorreu.
   * @return Uma resposta contendo um objeto StandardError com status HTTP 404 (Not Found).
   */
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,
      HttpServletRequest request) {
    StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),
        System.currentTimeMillis(),
        e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Trata exceções do tipo InvalidDataException.
   *
   * @param e       A exceção InvalidDataException lançada.
   * @param request A requisição HTTP onde a exceção ocorreu.
   * @return Uma resposta contendo um objeto StandardError com status HTTP 400 (Bad Request).
   */
  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<StandardError> invalidData(InvalidDataException e,
      HttpServletRequest request) {
    StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
        System.currentTimeMillis(), e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

}
