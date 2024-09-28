package com.mechachieve.vinculus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtividadeDaoVinculusLastLeg extends JpaRepository<AtividadeVinculus, Long> {
    
    @Query("SELECT a FROM AtividadeVinculus a WHERE a.clienteId = :clienteId AND a.date = :date ORDER BY a.id DESC Limit 1")
    Optional<AtividadeVinculus> findLastByClienteIdAndDate(@Param("clienteId") String clienteId, @Param("date") String date);
}


