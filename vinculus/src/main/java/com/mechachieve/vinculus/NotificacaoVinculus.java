package com.mechachieve.vinculus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotificacaoVinculus {

	private long id;
	private int notificacao;
	private String local;
	private String clienteId;
	private String dataAtual;
	private String horaAtual;

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
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}
	public String getHoraAtual() {
		return horaAtual;
	}
	public void setHoraAtual(String horaAtual) {
		this.horaAtual = horaAtual;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
}