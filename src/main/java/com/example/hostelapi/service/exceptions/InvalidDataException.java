package com.example.hostelapi.service.exceptions;

/**
 * Exceção personalizada para indicar dados inválidos.
 */
public class InvalidDataException extends RuntimeException {

  /**
   * Construtor.
   *
   * @param mensagem A mensagem de erro detalhada.
   */
  public InvalidDataException(String mensagem) {
    super(mensagem);
  }

  /**
   * Construtor.
   *
   * @param message A mensagem de erro detalhada.
   * @param cause A causa da exceção.
   */
  public InvalidDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
