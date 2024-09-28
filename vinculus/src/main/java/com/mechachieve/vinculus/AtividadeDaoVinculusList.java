package com.mechachieve.vinculus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeDaoVinculusList extends JpaRepository<AtividadeVinculus, Long>{
    List<AtividadeVinculus> findByClienteIdAndDateOrderByIdDesc(String clienteId, String date);
}