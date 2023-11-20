package com.almeida.os.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almeida.os.domain.Technician;
import com.almeida.os.dtos.TechnicianDTO;
import com.almeida.os.services.TechnicianService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/technicians")
public class TechnicianResource {
	
	@Autowired
	private TechnicianService technicianService; 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id){
		
		Technician technicianReturn = technicianService.findById(id);
		TechnicianDTO technicianDTOReturn = new TechnicianDTO(technicianReturn);
		return ResponseEntity.ok(technicianDTOReturn);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<TechnicianDTO>> findAll(){
		
		List<Technician> techniciansReturn = technicianService.findAll();
		
		List<TechnicianDTO> techniciansDTO = new ArrayList<>();
		
		for(Technician tec: techniciansReturn) {
			techniciansDTO.add(new TechnicianDTO(tec));
		}
		
		return ResponseEntity.ok(techniciansDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO technicianDTO){
		
		Technician technicianReturn = technicianService.create(technicianDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(technicianReturn.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> update(@Valid @RequestBody TechnicianDTO technicianDTO,@PathVariable Integer id){
		
		Technician technicianReturn = technicianService.update(technicianDTO, id);
		TechnicianDTO technicianDTONew = new TechnicianDTO(technicianReturn);
		return ResponseEntity.ok(technicianDTONew);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		technicianService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
}
