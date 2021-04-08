package com.kojitsystem.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kojitsystem.gestion.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
