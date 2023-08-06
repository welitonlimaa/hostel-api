package com.example.hostelapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nomeHospede;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataFim;

  private int quantidadePessoas;

  private String status;

}
