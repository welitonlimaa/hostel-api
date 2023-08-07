package com.example.hostelapi.controller;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReservaService reservaService;

  @InjectMocks
  private ReservaController reservaController;

  private ObjectMapper objectMapper;
  private ReservaDTO reservaDTO;
  private Reserva reserva;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();

    reservaDTO = new ReservaDTO();
    reservaDTO.setId(1);
    reservaDTO.setNomeHospede("Fulano de Tal");

    reserva = new Reserva(reservaDTO.getId(), reservaDTO.getNomeHospede(),
        reservaDTO.getDataInicio(), reservaDTO.getDataFim(),
        reservaDTO.getQuantidadePessoas(), reservaDTO.getStatus());
  }

  @Test
  public void testCreateReserva() throws Exception {
    when(reservaService.createReserva(any(ReservaDTO.class))).thenReturn(reserva);

    mockMvc.perform(post("/reservas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservaDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(reservaDTO.getId()))
        .andExpect(jsonPath("$.nomeHospede").value(reservaDTO.getNomeHospede()));

    verify(reservaService, times(1)).createReserva(any(ReservaDTO.class));
  }

  @Test
  public void testGetAllReservas() throws Exception {
    List<Reserva> reservas = new ArrayList<>();
    reservas.add(reserva);

    when(reservaService.getAllReservas()).thenReturn(reservas);

    mockMvc.perform(get("/reservas"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(reservaDTO.getId()))
        .andExpect(jsonPath("$[0].nomeHospede").value(reservaDTO.getNomeHospede()));

    verify(reservaService, times(1)).getAllReservas();
  }

  @Test
  public void testGetReservaById() throws Exception {
    when(reservaService.getReservaById(1)).thenReturn(reserva);

    mockMvc.perform(get("/reservas/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(reservaDTO.getId()))
        .andExpect(jsonPath("$.nomeHospede").value(reservaDTO.getNomeHospede()));

    verify(reservaService, times(1)).getReservaById(1);
  }

  @Test
  public void testUpdateReserva() throws Exception {
    when(reservaService.updateReserva(eq(1), any(ReservaDTO.class))).thenReturn(reserva);

    mockMvc.perform(put("/reservas/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservaDTO)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(reservaDTO.getId()))
        .andExpect(jsonPath("$.nomeHospede").value(reservaDTO.getNomeHospede()));

    verify(reservaService, times(1)).updateReserva(eq(1), any(ReservaDTO.class));
  }

  @Test
  public void testCancelarReserva() throws Exception {
    when(reservaService.cancelarReserva(1)).thenReturn(reserva);

    mockMvc.perform(delete("/reservas/1/cancelar"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(reservaDTO.getId()))
        .andExpect(jsonPath("$.nomeHospede").value(reservaDTO.getNomeHospede()));

    verify(reservaService, times(1)).cancelarReserva(1);
  }
}
