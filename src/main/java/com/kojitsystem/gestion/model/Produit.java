package com.kojitsystem.gestion.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity

@Table(name="t_Produit")
public class Produit extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = 2723572977273902717L;
	private String nom;
	private String description;
	private Double prix_unitaire;
	private Integer stock_quantite;
	@OneToMany(mappedBy="produit",targetEntity=Commande.class,fetch=FetchType.EAGER)
	
	@JsonIgnore
	private Collection<Commande> commandes;
	
	

	
	public Produit() { }
	
	public Produit(String nom, String description, Double prix_unitaire, Integer stock_quantite) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix_unitaire = prix_unitaire;
		this.stock_quantite = stock_quantite;
	}

	public Produit(String nom, String description, Double prix_unitaire, Integer stock_quantite,
			Collection<Commande> commandes) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix_unitaire = prix_unitaire;
		this.stock_quantite = stock_quantite;
		this.commandes = commandes;
	}

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrix_unitaire() {
		return prix_unitaire;
	}

	public void setPrix_unitaire(Double prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}

	public Collection<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}

	public Integer getStock_quantite() {
		return stock_quantite;
	}

	public void setStock_quantite(Integer stock_quantite) {
		this.stock_quantite = stock_quantite;
	}

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + nom + "\",\"description\":\"" + description
				+ "\",\"unitPrice\":\"" + prix_unitaire + "\",\"unitsInStock\":\"" + stock_quantite + "\",\"commandes\":\"" + commandes + "\"}";
	}
	
	
	
}
