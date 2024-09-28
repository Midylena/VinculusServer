package com.mechachieve.vinculus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotificacaoVinculus {

	private long id;
	private int notificacao;
	private String dataHoraAtual;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNotificacao() {
		return notificacao;
	}
	public void setNotificacao(int notificacao) {
		this.notificacao = notificacao;
	}
	public String getDataHoraAtual() {
		return dataHoraAtual;
	}
	public void setDataHoraAtual(String dataHoraAtual) {
		this.dataHoraAtual = dataHoraAtual;
	}
}