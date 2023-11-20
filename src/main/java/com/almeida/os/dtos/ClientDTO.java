package com.almeida.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.almeida.os.domain.Client;

import jakarta.validation.constraints.NotEmpty;

public class ClientDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "O Campo nome é requerido")
	private String name;

	@CPF
	@NotEmpty(message = "o campo cpf é requerido")
	private String cpf;
	
	@NotEmpty(message = "o campo phone é requerido")
	private String phone;

	public ClientDTO() {
		super();
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.phone = client.getPhone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
		
}
