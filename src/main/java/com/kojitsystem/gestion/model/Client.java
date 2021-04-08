package com.kojitsystem.gestion.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity

@Table(name="t_Client")
public class Client extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = -7464018122626100689L;
	private String nom;
	private String prenom;
	private String adresse;
	private String numero;
	@OneToMany(mappedBy="client",targetEntity=Commande.class,fetch=FetchType.EAGER)
	
	
	@JsonIgnore
	private Collection<Commande> commandes;
	
	public Client() {	}
	
	public Client(String nom, String prenom,String adresse , String numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numero = numero;
		
	}
		
	public Client(String nom, String prenom,String adresse , String numero, Collection<Commande> commandes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numero = numero;
		this.commandes = commandes;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Collection<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}
	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"nom\":\"" + nom + "\"}";
	}
}
