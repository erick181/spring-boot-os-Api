package com.almeida.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeida.os.domain.Client;
import com.almeida.os.domain.Technician;
import com.almeida.os.dtos.ClientDTO;
import com.almeida.os.repositories.ClientRepository;
import com.almeida.os.services.exceptions.DataIntegratyViolationException;
import com.almeida.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client findById(Integer id) {
		
		Optional<Client> clientReturn = clientRepository.findById(id);
		
		return clientReturn.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Technician.class.getName()));
		
		
	}
	public List<Client> findAll(){
		
		return clientRepository.findAll();
		
	}
	
	public Client create(ClientDTO clientDTO) {
		
		if(findByCPF(clientDTO) != null) {
			throw new DataIntegratyViolationException("Cpf ja cadastrado no sistema");
		}
		
		Client clientNew = new Client(null, clientDTO.getName(), clientDTO.getCpf(), clientDTO.getPhone());
		
		return clientRepository.save(clientNew);
		
	}
	
	public Client update(ClientDTO clientDTO, Integer id) {
		
		Client clientReturn = findById(id);
		
		if(findByCPF(clientDTO) != null && findByCPF(clientDTO).getId() != id) {
			throw new DataIntegratyViolationException("Cpf ja cadastrado no sistema");
		}
	
		clientReturn.setName(clientDTO.getName());
		clientReturn.setCpf(clientDTO.getCpf());
		clientReturn.setPhone(clientDTO.getPhone());
		
		return clientRepository.save(clientReturn);
		
	}
	
	public void delete(Integer id) {
		
		Client clientReturn = findById(id);
		
		if(clientReturn.getServicesOrders().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviço cadastrados, não pode ser deletedo!");
		}
		
		clientRepository.delete(clientReturn);
		
	}
	
	private Client findByCPF(ClientDTO clientDTO) {
		
		Client clientReturn = clientRepository.findByCPF(clientDTO.getCpf());
		if(clientReturn != null) {
			return clientReturn;
		}
		
		return null;
	}
}
