package com.example.hostelapi.dto;

import com.example.hostelapi.domain.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ReservaDTO implements Serializable {

  private Integer id;
  private String nomeHospede;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataFim;

  private Integer quantidadePessoas;
  private String status;

  public ReservaDTO(Reserva obj) {
    id = obj.getId();
    nomeHospede = obj.getNomeHospede();
    dataInicio = obj.getDataInicio();
    dataFim = obj.getDataFim();
    quantidadePessoas = obj.getQuantidadePessoas();
    status = obj.getStatus();
  }

  public ReservaDTO(Integer id, String nomeHospede, Date dataInicio, Date dataFim, Integer quantidadePessoas, String status) {
    this.id = id;
    this.nomeHospede = nomeHospede;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.quantidadePessoas = quantidadePessoas;
    this.status = status;
  }

  public ReservaDTO() {
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
