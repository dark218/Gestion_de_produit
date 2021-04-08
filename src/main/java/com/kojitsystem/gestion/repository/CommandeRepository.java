package com.kojitsystem.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kojitsystem.gestion.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
