package com.kojitsystem.gestion.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kojitsystem.gestion.model.Produit;
import com.kojitsystem.gestion.repository.ProduitRepository;



@RestController
@RequestMapping("api/")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping(value = "produits", method = RequestMethod.GET)
    public List<Produit> list() {
        return produitRepository.findAll();
    }

    @RequestMapping(value = "produits", method = RequestMethod.POST)
    public Produit create(@RequestBody Produit produit) {
        return produitRepository.saveAndFlush(produit);
    }

    @RequestMapping(value = "produits/{id}", method = RequestMethod.GET)
    public Produit get(@PathVariable Long id) {
        return produitRepository.findOne(id);
    }

    @RequestMapping(value = "produits/{id}", method = RequestMethod.PUT)
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        Produit existingProduit = produitRepository.findOne(id);
        BeanUtils.copyProperties(produit, existingProduit);
        return produitRepository.saveAndFlush(existingProduit);
    }

    @RequestMapping(value = "produits/{id}", method = RequestMethod.DELETE)
    public Produit delete(@PathVariable Long id) {
        Produit existingProduit = produitRepository.findOne(id);
        produitRepository.delete(existingProduit);
        return existingProduit;
    }
}
