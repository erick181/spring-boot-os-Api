package com.almeida.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almeida.os.domain.Client;
import com.almeida.os.domain.ServiceOrder;
import com.almeida.os.domain.Technician;
import com.almeida.os.domain.enuns.Priority;
import com.almeida.os.domain.enuns.Status;
import com.almeida.os.dtos.ServiceOrderDTO;
import com.almeida.os.repositories.ServiceOrderRepository;
import com.almeida.os.services.exceptions.ObjectNotFoundException;

@Service
public class ServiceOrderService {

	
	@Autowired 
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired 
	private TechnicianService technicianService;
	
	@Autowired 
	private ClientService clientService;
	
	
	public ServiceOrder findById(Integer id) {
		
		Optional<ServiceOrder> serviceOrderReturn = serviceOrderRepository.findById(id); 
		return serviceOrderReturn.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + ServiceOrder.class.getName()));
		
		
	}
	
	public List<ServiceOrder> findAll(){
		
		return serviceOrderRepository.findAll();
		
	}
	
	public ServiceOrder create(ServiceOrderDTO serviceOrderDTO){
		
		ServiceOrder serviceOrderNew = new ServiceOrder();
		
		serviceOrderNew.setId(serviceOrderDTO.getId());
		serviceOrderNew.setPriority(Priority.toEnum(serviceOrderDTO.getPriority().getCod()));
		serviceOrderNew.setObservacao(serviceOrderDTO.getObservacao());
		serviceOrderNew.setStatus(Status.toEnum(serviceOrderDTO.getStatus().getCod()));
		
		Technician technicianRertun = technicianService.findById(serviceOrderDTO.getTechnician());
		
		serviceOrderNew.setTechnician(technicianRertun);
		
		Client clientRertun = clientService.findById(serviceOrderDTO.getClient());
		
		serviceOrderNew.setClient(clientRertun);
		
		
		return serviceOrderRepository.save(serviceOrderNew);
		
	}
	
	public ServiceOrder update(ServiceOrderDTO serviceOrderDTO, Integer id) {
		
		ServiceOrder serviceOrderNew = findById(id);
		
		serviceOrderNew.setId(serviceOrderDTO.getId());
		serviceOrderNew.setPriority(Priority.toEnum(serviceOrderDTO.getPriority().getCod()));
		serviceOrderNew.setObservacao(serviceOrderDTO.getObservacao());
		serviceOrderNew.setStatus(Status.toEnum(serviceOrderDTO.getStatus().getCod()));
		
		if(serviceOrderNew.getStatus().getCod().equals("2")) {
			serviceOrderNew.setDataFechamento(LocalDateTime.now());
		}
		
		
		Technician technicianRertun = technicianService.findById(serviceOrderDTO.getTechnician());
		
		serviceOrderNew.setTechnician(technicianRertun);
		
		Client clientRertun = clientService.findById(serviceOrderDTO.getClient());
		
		serviceOrderNew.setClient(clientRertun);
		
		return serviceOrderRepository.save(serviceOrderNew);
		
	}
}
