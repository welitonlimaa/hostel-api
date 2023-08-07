package com.example.hostelapi.controller.exceptions;


/**
 * Classe que representa um erro padrão.
 */
public class StandardError {

  private Integer status;

  private Long horario;

  private String mensagem;

  /**
   * Construtor.
   *
   * @param status   O status HTTP associado ao erro.
   * @param horario  O horário em que o erro ocorreu.
   * @param mensagem A mensagem de erro detalhada.
   */
  public StandardError(Integer status, Long horario, String mensagem) {
    this.status = status;
    this.horario = horario;
    this.mensagem = mensagem;
  }

  public StandardError() {
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getHorario() {
    return horario;
  }

  public void setHorario(Long horario) {
    this.horario = horario;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
