package com.pedrochagas.desafioclient.services;

import com.pedrochagas.desafioclient.dtos.ClientDTO;
import com.pedrochagas.desafioclient.entities.Client;
import com.pedrochagas.desafioclient.repositories.ClientRepository;
import com.pedrochagas.desafioclient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClients(Pageable pageable){
        return clientRepository.findAll(pageable).map(client -> convertEntityToDto(client,new ClientDTO()));
    }

    @Transactional(readOnly = true)
    public ClientDTO findCLientById(Long id){
       Client client = clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
       return convertEntityToDto(client,new ClientDTO());
    }

    @Transactional(readOnly = false)
    public ClientDTO insertNewClient(ClientDTO clientDTO){
        Client client = clientRepository.save(convertDtoToEntity(clientDTO,new Client()));
        return convertEntityToDto(client,new ClientDTO());
    }

    @Transactional(readOnly = false)
    public void deleteClientById(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        clientRepository.deleteById(id);
    }


    public ClientDTO updateClient(Long id,ClientDTO clientDTO){
        Client client = clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
        clientRepository.save(convertDtoToEntity(clientDTO,client));
        return convertEntityToDto(client,clientDTO);
    }


    private ClientDTO convertEntityToDto(Client client,ClientDTO dto){
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setCpf(client.getCpf());
        dto.setIncome(client.getIncome());
        dto.setBirthDate(client.getBirthDate());
        dto.setChildren(client.getChildren());
        return dto;
    }
    private Client convertDtoToEntity(ClientDTO clientDTO,Client client){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
        return client;
    }
}
