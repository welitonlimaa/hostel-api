package com.example.hostelapi.service.exceptions;

public class InvalidDataException extends RuntimeException {
  public InvalidDataException(String mensagem) {
    super(mensagem);
  }

  public InvalidDataException(String message, Throwable cause) {
    super(message, cause);
  }
}

