package com.almeida.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeida.os.domain.Technician;
import com.almeida.os.dtos.TechnicianDTO;
import com.almeida.os.repositories.TechnicianRepository;
import com.almeida.os.services.exceptions.DataIntegratyViolationException;
import com.almeida.os.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository technicianRepository;
		
	public Technician findById(Integer id) {
		
		Optional<Technician> technicianReturn = technicianRepository.findById(id);
		return technicianReturn.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! ID: " + id + ", Tipo: " + Technician.class.getName()));
		
	}
	
	public List<Technician> findAll() {
		
		return technicianRepository.findAll();
		
	}
	
	public Technician create(TechnicianDTO technicianDTO) {
		
		if(findByCPF(technicianDTO) != null) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados");
		}
		
		Technician technician = new Technician(null, technicianDTO.getName(),
				technicianDTO.getCpf(),technicianDTO.getPhone());
		
		return technicianRepository.save(technician);
	}
	
	public Technician update(TechnicianDTO technicianDTO, Integer id) {
		
		Technician technicianReturn = findById(id);
		
		if(findByCPF(technicianDTO) != null && findByCPF(technicianDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados");
		}
		
		technicianReturn.setName(technicianDTO.getName());
		technicianReturn.setCpf(technicianDTO.getCpf());
		technicianReturn.setPhone(technicianDTO.getPhone());
		
		return technicianRepository.save(technicianReturn);
		
	}
	
	public void delete(Integer id) {
		
		Technician technicianReturn = findById(id);
		
		if(technicianReturn.getServicesOrders().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui ordens de serviço cadastrados, não pode ser deletedo!");
		}
		
		technicianRepository.delete(technicianReturn);
		
	}
	
	private Technician findByCPF(TechnicianDTO technicianDTO) {
		
		Technician technicianReturn = technicianRepository.findByCPF(technicianDTO.getCpf());
		if(technicianReturn != null) {
			return technicianReturn;
		}
		
		return null;
	}
	
}
