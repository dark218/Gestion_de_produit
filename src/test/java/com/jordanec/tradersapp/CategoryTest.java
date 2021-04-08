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
import com.kojitsystem.gestion.controller.ClientController;
import com.kojitsystem.gestion.model.Client;
import com.kojitsystem.gestion.model.Produit;
import com.kojitsystem.gestion.repository.ClientRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class CategoryTest {
	@InjectMocks
	private ClientController clientController;

	@Autowired
	private ClientRepository clientRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testCategoryCreate() {
		Client new_category = new Client();
		Client ret_new_category;
		new_category.setId(1l);
		new_category.setName("name");

		when(ret_new_category=clientRepository.save(new_category)).thenReturn(new_category);
		
		Client client = clientController.get(2L);

		verify(clientRepository).findOne(2l);		

		assertThat(client, is(ret_new_category));
		System.out.println(client);	
	}
	
	@Test
	public void testCategoryList() {
		Collection<Client> clients;
		Collection<Produit> produits;
		
		clients = clientRepository.findAll();
		for (Client client : clients) {
			System.out.println("\n<Category>");
			System.out.println("ID: "+ client.getId());
			System.out.println("Name: "+ client.getName());
			produits = client.getProducts();
			System.out.println("<Products>:");
			
			for(Produit produit: produits) {
				System.out.println("<Product>");
				System.out.println("ID: "+produit.getId());
				System.out.println("Name: "+produit.getName());
				System.out.println("Category: "+produit.getCategory().getName());
			}
		}
	}
}
