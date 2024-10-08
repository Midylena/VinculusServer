package com.mechachieve.vinculus;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificacaoDaoVinculusLast extends JpaRepository<NotificacaoVinculus, Long> {
	@Query(value = "select * from notificacao_Vinculus where id = (select max(id) from notificacao_Vinculus);", nativeQuery = true)
	Collection<NotificacaoVinculus> notificacao();
}