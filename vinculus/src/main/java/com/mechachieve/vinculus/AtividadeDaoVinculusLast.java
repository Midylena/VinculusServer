package com.mechachieve.vinculus;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtividadeDaoVinculusLast extends JpaRepository<AtividadeVinculus, Long> {
	@Query(value = "select * from atividade_Vinculus where id = (select max(id) from atividade_Vinculus);", nativeQuery = true)
	Collection<AtividadeVinculus> atividade();
}
