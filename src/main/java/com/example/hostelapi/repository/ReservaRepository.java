package com.example.hostelapi.repository;

import com.example.hostelapi.domain.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
  List<Reserva> findByStatusIgnoreCase(String status);
}
