package com.example.hostelapi.controller;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

  private final ReservaService reservaService;

  @Autowired
  public ReservaController(ReservaService reservaService) {
    this.reservaService = reservaService;
  }

  @PostMapping
  public ResponseEntity<Reserva> createReserva(@Validated @RequestBody ReservaDTO reservaDTO) {
    Reserva novaReserva = reservaService.createReserva(reservaDTO);
    return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
  }
}
