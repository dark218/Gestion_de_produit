package com.kojitsystem.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kojitsystem.gestion.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
