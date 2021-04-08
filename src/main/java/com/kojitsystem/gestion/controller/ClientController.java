package com.kojitsystem.gestion.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kojitsystem.gestion.model.Client;
import com.kojitsystem.gestion.repository.ClientRepository;


@RestController
@RequestMapping("api/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @RequestMapping(value = "clients", method = RequestMethod.POST)
    public Client create(@RequestBody Client client) {
        return clientRepository.saveAndFlush(client);
    }

    @RequestMapping(value = "clients/{id}", method = RequestMethod.GET)
    public Client get(@PathVariable Long id) {
        return clientRepository.findOne(id);
    }

    @RequestMapping(value = "clients/{id}", method = RequestMethod.PUT)
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        Client existingClient = clientRepository.findOne(id);
        BeanUtils.copyProperties(client, existingClient);
        return clientRepository.saveAndFlush(existingClient);
    }

    @RequestMapping(value = "clients/{id}", method = RequestMethod.DELETE)
    public Client delete(@PathVariable Long id) {
        Client existingClient = clientRepository.findOne(id);
        clientRepository.delete(existingClient);
        return existingClient;
    }
}
