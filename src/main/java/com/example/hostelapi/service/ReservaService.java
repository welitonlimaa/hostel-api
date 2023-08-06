package com.example.hostelapi.service;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.repository.ReservaRepository;
import com.example.hostelapi.service.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class ReservaService {

  private final ReservaRepository reservaRepository;

  @Autowired
  public ReservaService(ReservaRepository reservaRepository) {
    this.reservaRepository = reservaRepository;
  }

  public Reserva createReserva(ReservaDTO reservaDto) {
      LocalDate dataInicio = LocalDate.parse((reservaDto.getDataInicio()).toString());
      LocalDate dataFim = LocalDate.parse((reservaDto.getDataFim()).toString());

      if (dataFim.isBefore(dataInicio)) {
        throw new InvalidDataException("A data de fim deve ser posterior à data de início.");
      }

      Reserva reserva = new Reserva(null, reservaDto.getNomeHospede(), dataInicio, dataFim, reservaDto.getQuantidadePessoas(), "CONFIRMADA");
      return reservaRepository.save(reserva);
  }
}
