package com.example.hostelapi.controller;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.service.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por manipular requisições relacionadas as reservas. Mapeia endpoints e
 * gerencia a interação com a camada service.
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

  private final ReservaService reservaService;

  /**
   * Construtor.
   *
   * @param reservaService O serviço a ser utilizado pelo controlador.
   */
  @Autowired
  public ReservaController(ReservaService reservaService) {
    this.reservaService = reservaService;
  }

  /**
   * Cria uma nova reserva com base nos dados fornecidos no corpo da requisição.
   *
   * @param reservaDTO O DTO contendo os detalhes da reserva a ser criada.
   * @return Uma resposta com a reserva criada e o status HTTP 201 (Created).
   */
  @PostMapping
  public ResponseEntity<Reserva> createReserva(@Validated @RequestBody ReservaDTO reservaDTO) {
    Reserva novaReserva = reservaService.createReserva(reservaDTO);
    return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
  }

  /**
   * Obtém todas as reservas.
   *
   * @return Uma resposta com a lista de todas as reservas existentes.
   */
  @GetMapping
  public ResponseEntity<List<Reserva>> getAllReservas() {
    List<Reserva> reservas = reservaService.getAllReservas();
    return ResponseEntity.ok(reservas);
  }

  /**
   * Obtém uma reserva pelo seu ID.
   *
   * @param id O ID da reserva a ser obtida.
   * @return Uma resposta com a reserva encontrada.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Reserva> getReservaById(@PathVariable Integer id) {
    Reserva reserva = reservaService.getReservaById(id);
    return ResponseEntity.ok(reserva);
  }

  /**
   * Atualiza uma reserva com base nos dados fornecidos no corpo da requisição.
   *
   * @param id         O ID da reserva a ser atualizada.
   * @param reservaDTO O DTO contendo os novos detalhes da reserva.
   * @return Uma resposta com a reserva atualizada.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Reserva> updateReserva(@PathVariable Integer id,
      @Validated @RequestBody ReservaDTO reservaDTO) {
    Reserva reservaAtualizada = reservaService.updateReserva(id, reservaDTO);
    return ResponseEntity.ok(reservaAtualizada);
  }

  /**
   * Cancela uma reserva pelo seu ID.
   *
   * @param id O ID da reserva a ser cancelada.
   * @return Uma resposta com a reserva cancelada.
   */
  @DeleteMapping("/{id}/cancelar")
  public ResponseEntity<Reserva> cancelarReserva(@PathVariable Integer id) {
    Reserva reservaCancelada = reservaService.cancelarReserva(id);
    return ResponseEntity.ok(reservaCancelada);
  }

  /**
   * Obtém uma lista de reservas com base no status fornecido.
   *
   * @param status O status das reservas a serem buscadas. A comparação é case-sensitive.
   * @return Uma resposta ResponseEntity contendo uma lista de reservas com o status correspondente
   * e o status HTTP 200.
   */
  @GetMapping("/status")
  public ResponseEntity<List<Reserva>> getReservasByStatus(
      @RequestParam(name = "status") String status) {
    List<Reserva> reservas = reservaService.getReservasByStatus(status);
    return ResponseEntity.ok(reservas);
  }

}
