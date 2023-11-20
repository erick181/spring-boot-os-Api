package com.almeida.os.domain;

import java.time.LocalDateTime;

import com.almeida.os.domain.enuns.Priority;
import com.almeida.os.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm")
	private LocalDateTime dataAbertuda;
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer priority;
	private String observacao;
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public ServiceOrder() {
		super();
		this.setDataAbertuda(LocalDateTime.now());
		this.setPriority(Priority.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public ServiceOrder(Integer id, Priority priority,
			String observacao, Status status) {
		super();
		this.id = id;
		this.dataAbertuda = LocalDateTime.now();
		this.priority = (priority == null) ? 0 : priority.getCod();
		this.observacao = observacao;
		this.status = (status == null)? 0 : status.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public void setPriority(Priority priority) {
		this.priority = priority.getCod();
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
}
