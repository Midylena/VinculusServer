package com.mechachieve.vinculus;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoDaoVinculusList extends JpaRepository<NotificacaoVinculus, Long>{
	List<NotificacaoVinculus> findByClienteIdAndDataAtualOrderByIdDesc(String clienteId, String dataAtual);
}