package com.jordanec.tradersapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kojitsystem.gestion.App;
import com.kojitsystem.gestion.controller.CommandeController;
import com.kojitsystem.gestion.model.Produit;
import com.kojitsystem.gestion.model.Commande;
import com.kojitsystem.gestion.repository.CommandeRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class SupplierTest {
	@InjectMocks
	private CommandeController commandeController;

	@Autowired
	private CommandeRepository commandeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testSupplierCreate() {
		Commande new_supplier = new Commande();
		Commande ret_new_supplier;
		//new_supplier.setId((long)5);
		new_supplier.setName("name");
		new_supplier.setAddress("address");
		new_supplier.setCompany("company");
		new_supplier.setPhone(88997744);

		//when(ret_new_supplier=supplierRepository.save(new_supplier)).thenReturn(new_supplier);
		ret_new_supplier=commandeRepository.save(new_supplier);
		Commande commande = commandeRepository.findOne(ret_new_supplier.getId());
		//Supplier wreck = supplierController.get(ret_new_supplier.getId());
		
		//verify(supplierRepository).findOne(ret_new_supplier.getId());
		
		assertThat(commande, is(ret_new_supplier));
		System.out.println(commande);
	}
	
	@Test
	public void testSupplierList() {
		Collection<Commande> commandes;
		Collection<Produit> produits;
		
		commandes = commandeRepository.findAll();
		
		for (Commande commande : commandes) {
			System.out.println("\n<Supplier>");
			System.out.println("ID: "+ commande.getId());
			System.out.println("Name: "+ commande.getName());
			System.out.println("Company: "+ commande.getCompany());
			System.out.println("Phone: "+ commande.getPhone());
			produits = commande.getProducts();
			System.out.println("<Products>:");
			
			for(Produit produit: produits) {
				System.out.println("<Product>");
				System.out.println("ID: "+produit.getId());
				System.out.println("Name: "+produit.getName());
				System.out.println("Category: "+produit.getCategory().getName());
			}
		}
	}
	
	@Test
	public void testSupplierUpdate() {
		Commande commande = commandeRepository.findOne(1l);
		commande.setName(commande.getName()+"_UPDATED");
		commandeRepository.save(commande);
	}
}