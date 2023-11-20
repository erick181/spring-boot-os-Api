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

import com.almeida.os.domain.Client;
import com.almeida.os.dtos.ClientDTO;
import com.almeida.os.services.ClientService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
		
		Client clienteReturn = clientService.findById(id);
		ClientDTO clientDTO = new ClientDTO(clienteReturn);
		return ResponseEntity.ok(clientDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		
		List<Client> clientsReturn = clientService.findAll();
		List<ClientDTO> clientsDTO = new ArrayList<>();
		
			for(Client cli: clientsReturn) {
				clientsDTO.add(new ClientDTO(cli));
			}
		
			return ResponseEntity.ok(clientsDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO){
		
		Client clientReturn = clientService.create(clientDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(clientReturn.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO,@PathVariable Integer id){
		
		Client clientReturn = clientService.update(clientDTO, id);
		
		ClientDTO clientDTONew = new ClientDTO(clientReturn);
		
		return ResponseEntity.ok(clientDTONew);
		
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
}
