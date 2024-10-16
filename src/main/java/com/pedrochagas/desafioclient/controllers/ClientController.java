package com.pedrochagas.desafioclient.controllers;

import com.pedrochagas.desafioclient.dtos.ClientDTO;
import com.pedrochagas.desafioclient.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClients(Pageable pageable) {
        Page<ClientDTO> clientDTOPage = clientService.findAllClients(pageable);
        return ResponseEntity.ok(clientDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findCLientById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insertNewClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO dto = clientService.insertNewClient(clientDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build(dto);
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,@Valid @RequestBody ClientDTO clientDTO){
        ClientDTO dto = clientService.updateClient(id,clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id){
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
