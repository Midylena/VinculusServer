package com.mechachieve.vinculus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoDaoVinculusGet extends JpaRepository<HistoricoVinculus, Long> {
	Optional<HistoricoVinculus> findFirstByClienteIdOrderByIdDesc(String clienteId);
}