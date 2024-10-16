package com.pedrochagas.desafioclient.repositories;

import com.pedrochagas.desafioclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client,Long> {
}
