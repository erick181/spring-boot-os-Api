package com.almeida.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person implements Serializable {


	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "client")
	List<ServiceOrder> servicesOrders = new ArrayList<>();
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
		// TODO Auto-generated constructor stub
	}

	public List<ServiceOrder> getServicesOrders() {
		return servicesOrders;
	}

	public void setServicesOrders(List<ServiceOrder> servicesOrders) {
		this.servicesOrders = servicesOrders;
	}
	
}
