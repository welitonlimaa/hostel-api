package com.example.hostelapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Classe que representa uma reserva de hospedagem.
 */
@Entity
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nomeHospede;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataFim;

  private Integer quantidadePessoas;

  private String status;

  /**
   * Construtor.
   *
   * @param id                O ID da reserva.
   * @param nomeHospede       O nome do hospede.
   * @param dataInicio        A data de inicio da reserva.
   * @param dataFim           A data de fim da reserva.
   * @param quantidadePessoas A quantidade de pessoas na reserva.
   * @param status            O status da reserva.
   */
  public Reserva(Integer id, String nomeHospede, Date dataInicio, Date dataFim,
      Integer quantidadePessoas, String status) {
    this.id = id;
    this.nomeHospede = nomeHospede;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.quantidadePessoas = quantidadePessoas;
    this.status = status;
  }

  public Reserva() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNomeHospede() {
    return nomeHospede;
  }

  public void setNomeHospede(String nomeHospede) {
    this.nomeHospede = nomeHospede;
  }

  public Date getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  public Date getDataFim() {
    return dataFim;
  }

  public void setDataFim(Date dataFim) {
    this.dataFim = dataFim;
  }

  public Integer getQuantidadePessoas() {
    return quantidadePessoas;
  }

  public void setQuantidadePessoas(Integer quantidadePessoas) {
    this.quantidadePessoas = quantidadePessoas;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
