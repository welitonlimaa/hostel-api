package com.example.hostelapi.dto;

import com.example.hostelapi.domain.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class ReservaDTO implements Serializable {

  private Integer id;
  private String nomeHospede;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataFim;

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

  public ReservaDTO(Integer id, String nomeHospede, LocalDate dataInicio, LocalDate dataFim, Integer quantidadePessoas, String status) {
    this.id = id;
    this.nomeHospede = nomeHospede;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.quantidadePessoas = quantidadePessoas;
    this.status = status;
  }
}
