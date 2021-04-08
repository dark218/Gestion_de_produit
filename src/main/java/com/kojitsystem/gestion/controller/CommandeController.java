package com.kojitsystem.gestion.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kojitsystem.gestion.model.Commande;
import com.kojitsystem.gestion.repository.CommandeRepository;



@RestController
@RequestMapping("api/")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @RequestMapping(value = "commandes", method = RequestMethod.GET)
    public List<Commande> list() {
        return commandeRepository.findAll();
    }

    @RequestMapping(value = "commandes", method = RequestMethod.POST)
    public Commande create(@RequestBody Commande commande) {
        return commandeRepository.saveAndFlush(commande);
    }

    @RequestMapping(value = "commandes/{id}", method = RequestMethod.GET)
    public Commande get(@PathVariable Long id) {
        return commandeRepository.findOne(id);
    }

    @RequestMapping(value = "commandes/{id}", method = RequestMethod.PUT)
    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
        Commande existingCommande = commandeRepository.findOne(id);
        BeanUtils.copyProperties(commande, existingCommande);
        return commandeRepository.saveAndFlush(existingCommande);
    }

    @RequestMapping(value = "commandes/{id}", method = RequestMethod.DELETE)
    public Commande delete(@PathVariable Long id) {
        Commande existingCommande = commandeRepository.findOne(id);
        commandeRepository.delete(existingCommande);
        return existingCommande;
    }
}
