package model;

import java.util.Date;

public class User {
	private String nom;
	private String prenom;
	private Date dnaissance;
	private String email;
	private String mdp;
	
	public User(String nom, String prenom, Date dnaissance, String email, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.dnaissance = dnaissance;
		this.email = email;
		this.mdp = mdp;
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
	public Date getDnaissance() {
		return dnaissance;
	}
	public void setDnaissance(Date dnaissance) {
		this.dnaissance = dnaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
