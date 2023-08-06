package com.example.hostelapi.controller.exceptions;

import com.example.hostelapi.service.exceptions.InvalidDataException;
import com.example.hostelapi.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
    StandartError erro = new StandartError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<StandartError> invalidData(InvalidDataException e, HttpServletRequest request){
    StandartError erro = new StandartError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
  }

}

