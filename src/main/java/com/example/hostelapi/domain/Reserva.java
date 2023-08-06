package com.example.hostelapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nomeHospede;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataFim;

  private Integer quantidadePessoas;

  private String status;

  public Reserva(Integer id, String nomeHospede, LocalDate dataInicio, LocalDate dataFim,
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

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDataFim() {
    return dataFim;
  }

  public void setDataFim(LocalDate dataFim) {
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
