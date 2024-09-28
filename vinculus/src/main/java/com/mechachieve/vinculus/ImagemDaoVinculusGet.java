package com.mechachieve.vinculus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemDaoVinculusGet extends JpaRepository<ImagemVinculus, Long> {
	Optional<ImagemVinculus> findImagemByClienteId(String clienteId);
}

