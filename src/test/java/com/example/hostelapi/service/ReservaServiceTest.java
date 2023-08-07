package com.example.hostelapi.service;

import com.example.hostelapi.domain.Reserva;
import com.example.hostelapi.dto.ReservaDTO;
import com.example.hostelapi.repository.ReservaRepository;
import com.example.hostelapi.service.exceptions.InvalidDataException;
import com.example.hostelapi.service.exceptions.ObjectNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservaServiceTest {

  @Mock
  private ReservaRepository reservaRepository;

  @InjectMocks
  private ReservaService reservaService;

  private ReservaDTO reservaDTO;
  private Reserva reserva;

  @BeforeEach
  public void setUp() {
    reservaDTO = new ReservaDTO();
    reservaDTO.setNomeHospede("Fulano de Tal");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      reservaDTO.setDataInicio(dateFormat.parse("2023-08-10"));
      reservaDTO.setDataFim(dateFormat.parse("2023-08-15"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    reservaDTO.setQuantidadePessoas(4);
    reservaDTO.setStatus("CONFIRMADA");

    reserva = new Reserva(1, reservaDTO.getNomeHospede(), reservaDTO.getDataInicio(), reservaDTO.getDataFim(),
        reservaDTO.getQuantidadePessoas(), reservaDTO.getStatus());
  }

  @Test
  public void testCreateReserva() throws ParseException {
    when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dataInicio = dateFormat.parse("2023-08-10");
    Date dataFim = dateFormat.parse("2023-08-15");

    ReservaDTO reservaDTO = new ReservaDTO();
    reservaDTO.setNomeHospede("Fulano de Tal");
    reservaDTO.setDataInicio(dataInicio);
    reservaDTO.setDataFim(dataFim);
    reservaDTO.setQuantidadePessoas(4);
    reservaDTO.setStatus("CONFIRMADA");

    Reserva novaReserva = reservaService.createReserva(reservaDTO);
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    assertNotNull(novaReserva);
    assertEquals(reservaDTO.getNomeHospede(), novaReserva.getNomeHospede());
    assertEquals(dataFormat.format(dataInicio), dataFormat.format(novaReserva.getDataInicio()));
    assertEquals(dataFormat.format(dataFim), dataFormat.format(novaReserva.getDataFim()));
    assertEquals(reservaDTO.getQuantidadePessoas(), novaReserva.getQuantidadePessoas());
    assertEquals(reservaDTO.getStatus(), novaReserva.getStatus());

    verify(reservaRepository, times(1)).save(any(Reserva.class));
  }

  @Test
  public void testTryCreateReserva_InvalidDataException() {
    reservaDTO.setDataFim(new Date(2023 - 1900, 7, 9));

    assertThrows(InvalidDataException.class, () -> reservaService.createReserva(reservaDTO));

    verify(reservaRepository, never()).save(any(Reserva.class));
  }

  @Test
  public void testGetAllReservas() {
    List<Reserva> reservas = new ArrayList<>();
    reservas.add(reserva);

    when(reservaRepository.findAll()).thenReturn(reservas);

    List<Reserva> resultado = reservaService.getAllReservas();

    assertEquals(1, resultado.size());
    assertEquals(reserva, resultado.get(0));

    verify(reservaRepository, times(1)).findAll();
  }

  @Test
  public void testGetReservaById() {
    when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

    Reserva reservaEncontrada = reservaService.getReservaById(1);

    assertNotNull(reservaEncontrada);
    assertEquals(reserva, reservaEncontrada);

    verify(reservaRepository, times(1)).findById(1);
  }

  @Test
  public void testTryGetReservaById_ObjectNotFoundException() {
    when(reservaRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ObjectNotFoundException.class, () -> reservaService.getReservaById(1));

    verify(reservaRepository, times(1)).findById(1);
  }

  @Test
  public void testUpdateReserva() {
    when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
    when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

    ReservaDTO reservaAtualizadaDTO = new ReservaDTO();
    reservaAtualizadaDTO.setNomeHospede("Fulano da Silva");
    reservaAtualizadaDTO.setDataInicio(new Date(2023, 8, 12));
    reservaAtualizadaDTO.setDataFim(new Date(2023, 8, 17));
    reservaAtualizadaDTO.setQuantidadePessoas(5);
    reservaAtualizadaDTO.setStatus("PENDENTE");

    Reserva reservaAtualizada = reservaService.updateReserva(1, reservaAtualizadaDTO);

    assertNotNull(reservaAtualizada);
    assertEquals(reserva.getId(), reservaAtualizada.getId());
    assertEquals(reservaAtualizadaDTO.getNomeHospede(), reservaAtualizada.getNomeHospede());
    assertEquals(reservaAtualizadaDTO.getDataInicio(), reservaAtualizada.getDataInicio());
    assertEquals(reservaAtualizadaDTO.getDataFim(), reservaAtualizada.getDataFim());
    assertEquals(reservaAtualizadaDTO.getQuantidadePessoas(), reservaAtualizada.getQuantidadePessoas());
    assertEquals(reservaAtualizadaDTO.getStatus(), reservaAtualizada.getStatus());

    verify(reservaRepository, times(1)).findById(1);
    verify(reservaRepository, times(1)).save(any(Reserva.class));
  }

  @Test
  public void testTryUpdateReserva_ObjectNotFoundException() {
    when(reservaRepository.findById(1)).thenReturn(Optional.empty());

    ReservaDTO reservaAtualizadaDTO = new ReservaDTO();
    reservaAtualizadaDTO.setNomeHospede("Fulano da Silva");
    reservaAtualizadaDTO.setDataInicio(new Date(2023, 8, 12));
    reservaAtualizadaDTO.setDataFim(new Date(2023, 8, 17));
    reservaAtualizadaDTO.setQuantidadePessoas(5);
    reservaAtualizadaDTO.setStatus("PENDENTE");

    assertThrows(ObjectNotFoundException.class, () -> reservaService.updateReserva(1, reservaAtualizadaDTO));

    verify(reservaRepository, times(1)).findById(1);
    verify(reservaRepository, never()).save(any(Reserva.class));
  }

  @Test
  public void testCancelarReserva() {
    when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
    when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

    Reserva reservaCancelada = reservaService.cancelarReserva(1);

    assertNotNull(reservaCancelada);
    assertEquals("CANCELADA", reservaCancelada.getStatus());

    verify(reservaRepository, times(1)).findById(1);
    verify(reservaRepository, times(1)).save(any(Reserva.class));
  }

  @Test
  public void testTryCancelarReserva_ObjectNotFoundException() {
    when(reservaRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ObjectNotFoundException.class, () -> reservaService.cancelarReserva(1));

    verify(reservaRepository, times(1)).findById(1);
    verify(reservaRepository, never()).save(any(Reserva.class));
  }
}

