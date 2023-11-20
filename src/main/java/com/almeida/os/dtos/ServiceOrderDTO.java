package com.almeida.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.almeida.os.domain.ServiceOrder;
import com.almeida.os.domain.enuns.Priority;
import com.almeida.os.domain.enuns.Status;

public class ServiceOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDateTime dataAbertuda;

	private LocalDateTime dataFechamento;
	private Integer priority;
	private String observacao;
	private Integer status;

	private Integer technician;

	private Integer client;

	public ServiceOrderDTO() {
		super();
	}

	public ServiceOrderDTO(ServiceOrder serviceOrder) {
		super();
		this.id = serviceOrder.getId();
		this.dataAbertuda = serviceOrder.getDataAbertuda();
		this.dataFechamento = serviceOrder.getDataFechamento();
		this.priority = serviceOrder.getPriority().getCod();
		this.observacao = serviceOrder.getObservacao();
		this.status = serviceOrder.getStatus().getCod();
		this.technician = serviceOrder.getTechnician().getId();
		this.client = serviceOrder.getClient().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertuda() {
		return dataAbertuda;
	}

	public void setDataAbertuda(LocalDateTime dataAbertuda) {
		this.dataAbertuda = dataAbertuda;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Priority getPriority() {
		return Priority.toEnum(this.priority);
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTechnician() {
		return technician;
	}

	public void setTechnician(Integer technician) {
		this.technician = technician;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

}
