package com.example.hostelapi.service;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.repository.ReservaRepository;
import com.example.hostelapi.service.exceptions.InvalidDataException;
import com.example.hostelapi.service.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

  public List<Reserva> getAllReservas() {
    return reservaRepository.findAll();
  }

  public Reserva getReservaById(Integer id) {
    Optional<Reserva> reservaOptional = reservaRepository.findById(id);
    return reservaOptional.orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada. ID: " + id));
  }

  public Reserva updateReserva(Integer id, ReservaDTO reservaDTO) {
    Reserva reserva = getReservaById(id);
    reserva.setNomeHospede(reservaDTO.getNomeHospede());
    reserva.setDataInicio(reservaDTO.getDataInicio());
    reserva.setDataFim(reservaDTO.getDataFim());
    reserva.setQuantidadePessoas(reservaDTO.getQuantidadePessoas());
    reserva.setStatus(reservaDTO.getStatus());
    return reservaRepository.save(reserva);
  }
}
