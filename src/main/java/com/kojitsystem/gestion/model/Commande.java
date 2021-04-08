package com.kojitsystem.gestion.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity

@Table(name="t_Commande")
public class Commande extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = 6414688052317334163L;
	private String quantite;
	@ManyToOne
	
	private Client client;
	@ManyToOne
	
	private Produit produit;
	
	
	public Commande() {	}
	
	public Commande(String quantite) {
		super();
		this.quantite = quantite;
		
	}
	public Commande(String quantite,Client client) {
		super();
		this.quantite = quantite;
		this.client = client;
		
	}
	public Commande(String quantite,Client client, Produit produit) {
		super();
		this.quantite = quantite;
		this.client = client;
		this.produit = produit;
	}
	
	public String getQuantite() {
		return quantite;
	}
	
	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + quantite + "\",\"client\":\"" + client + "\",\"produit\":\"" + produit + "\"}";
	}
}
