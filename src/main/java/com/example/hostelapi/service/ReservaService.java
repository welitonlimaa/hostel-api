package com.example.hostelapi.service;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.repository.ReservaRepository;
import com.example.hostelapi.service.exceptions.InvalidDataException;
import com.example.hostelapi.service.exceptions.ObjectNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Serviço responsável por operações relacionadas a reservas.
 * Implementa a regra de negócios para criar, buscar, atualizar e cancelar reservas.
 */
@Service
public class ReservaService {

  private final ReservaRepository reservaRepository;

  /**
   * Construtor.
   *
   * @param reservaRepository O repositório a ser utilizado pelo serviço.
   */
  @Autowired
  public ReservaService(ReservaRepository reservaRepository) {
    this.reservaRepository = reservaRepository;
  }

  /**
   * Cria uma nova reserva com base nos dados fornecidos no DTO.
   *
   * @param reservaDto O DTO contendo os detalhes da reserva a ser criada.
   * @return A reserva criada.
   * @throws InvalidDataException Se os dados fornecidos forem inválidos.
   */
  public Reserva createReserva(ReservaDTO reservaDto) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date dataInicio = dateFormat.parse(dateFormat.format(reservaDto.getDataInicio()));
      Date dataFim = dateFormat.parse(dateFormat.format(reservaDto.getDataFim()));

      if (dataFim.before(dataInicio)) {
        throw new InvalidDataException("A data de fim deve ser posterior à data de início.");
      }

      Reserva reserva = new Reserva(
          null,
          reservaDto.getNomeHospede(),
          dataInicio,
          dataFim,
          reservaDto.getQuantidadePessoas(),
          "CONFIRMADA"
      );

      return reservaRepository.save(reserva);
    } catch (ParseException e) {
      throw new InvalidDataException("Formato de data inválido. Use o formato yyyy-MM-dd.");
    }
  }

  /**
   * Obtém todas as reservas existentes.
   *
   * @return Uma lista contendo todas as reservas.
   */
  public List<Reserva> getAllReservas() {
    return reservaRepository.findAll();
  }

  /**
   * Obtém uma reserva pelo seu ID.
   *
   * @param id O ID da reserva a ser obtida.
   * @return A reserva encontrada.
   * @throws ObjectNotFoundException Se a reserva com o ID fornecido não for encontrada.
   */
  public Reserva getReservaById(Integer id) {
    Optional<Reserva> reservaOptional = reservaRepository.findById(id);
    return reservaOptional.orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada. ID: " + id));
  }

  /**
   * Atualiza uma reserva com base nos dados fornecidos no DTO.
   *
   * @param id O ID da reserva a ser atualizada.
   * @param reservaDTO O DTO contendo os novos detalhes da reserva.
   * @return A reserva atualizada.
   * @throws ObjectNotFoundException Se a reserva com o ID fornecido não for encontrada.
   */
  public Reserva updateReserva(Integer id, ReservaDTO reservaDTO) {
    Reserva reserva = getReservaById(id);
    reserva.setNomeHospede(reservaDTO.getNomeHospede());
    reserva.setDataInicio(reservaDTO.getDataInicio());
    reserva.setDataFim(reservaDTO.getDataFim());
    reserva.setQuantidadePessoas(reservaDTO.getQuantidadePessoas());
    reserva.setStatus(reservaDTO.getStatus());
    return reservaRepository.save(reserva);
  }

  /**
   * Cancela uma reserva pelo seu ID.
   *
   * @param id O ID da reserva a ser cancelada.
   * @return A reserva cancelada.
   * @throws ObjectNotFoundException Se a reserva com o ID fornecido não for encontrada.
   */
  public Reserva cancelarReserva(Integer id) {
    Reserva reserva = getReservaById(id);
    reserva.setStatus("CANCELADA");
    return reservaRepository.save(reserva);
  }

  /**
   * Obtém uma lista de reservas com base no status fornecido.
   *
   * @param status O status das reservas a serem buscadas. A comparação é case-insensitive.
   * @return Uma lista contendo as reservas com o status correspondente.
   */
  public List<Reserva> getReservasByStatus(String status) {
    return reservaRepository.findByStatusIgnoreCase(status);
  }

}
