package com.almeida.os.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almeida.os.domain.ServiceOrder;
import com.almeida.os.dtos.ServiceOrderDTO;
import com.almeida.os.services.ServiceOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/services-orders")
public class ServiceOrderResource {

	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ServiceOrderDTO> findById(@PathVariable Integer id){
		
		ServiceOrder serviceOrderReturn = serviceOrderService.findById(id);
		ServiceOrderDTO serviceOrderDTONew = new ServiceOrderDTO(serviceOrderReturn);
		
		return ResponseEntity.ok(serviceOrderDTONew);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<ServiceOrderDTO>> findAll(){
		
		List<ServiceOrder> servicesOdersReturn = serviceOrderService.findAll();
		List<ServiceOrderDTO> servicesOdersDTONew = new ArrayList<>();
		
		for(ServiceOrder so: servicesOdersReturn) {
			servicesOdersDTONew.add(new ServiceOrderDTO(so));
		}
		
		return ResponseEntity.ok(servicesOdersDTONew);
		
	}
	
	@PostMapping
	public ResponseEntity<ServiceOrderDTO> create(@RequestBody ServiceOrderDTO serviceOrderDTO){
		
		ServiceOrder serviceOrderReturn = serviceOrderService.create(serviceOrderDTO);
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(serviceOrderReturn.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ServiceOrderDTO> update(@RequestBody ServiceOrderDTO serviceOrderDTO, Integer id){
		
		ServiceOrder serviceOrderReturn = serviceOrderService.update(serviceOrderDTO, id);
		ServiceOrderDTO serviceOrderDTONew = new ServiceOrderDTO(serviceOrderReturn);
		
		return ResponseEntity.ok(serviceOrderDTONew);
	}
	
}
