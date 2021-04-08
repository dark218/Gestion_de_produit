package com.jordanec.tradersapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kojitsystem.gestion.App;
import com.kojitsystem.gestion.controller.ProduitController;
import com.kojitsystem.gestion.model.Produit;
import com.kojitsystem.gestion.model.Commande;
import com.kojitsystem.gestion.repository.ProduitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
//@SpringApplicationConfiguration(App.class)
public class ProductTest {
	@InjectMocks
	private ProduitController produitController;
	
	@Autowired
	private ProduitRepository produitRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testProductCreate() {
		long lo = 9L;
		Produit new_product = new Produit();
		Produit ret_new_product;
		new_product.setId(lo);
		new_product.setName("name");
		new_product.setDescription("description");
		new_product.setUnitPrice(25.0);
		new_product.setUnitsInStock(10);

		when(ret_new_product=produitRepository.save(new_product)).thenReturn(new_product);
		
		Produit produit = produitController.get(lo);

		verify(produitRepository).findOne(lo);		

		assertThat(produit, is(ret_new_product));
		System.out.println(produit);
	}
	
	@Test
	public void testProductList() {
		Collection<Produit> produits;
		Collection<Commande> commandes;
		
		produits = produitRepository.findAll();
		for (Produit produit : produits) {
			System.out.println("\n<Product>");
			System.out.println("ID: "+ produit.getId());
			System.out.println("Name: "+ produit.getName());
			System.out.println("UnitPrice: "+ produit.getUnitPrice());
			System.out.println("UnitsInStock: "+ produit.getUnitsInStock());
			System.out.println("Category: "+ produit.getCategory());
			
			commandes = produit.getSuppliers();
			System.out.println("<Suppliers>:");
			
			for(Commande commande: commandes) {
				System.out.println("<Supplier>");
				System.out.println("ID: "+commande.getId());
				System.out.println("Name: "+commande.getName());
			}
		}
	}
}
