package cosport;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Annonce {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	//private Terrain terrain;
	private Sport sport;
	private String nom;
	private int nbMaxParticipant;
	
	@ManyToOne
	Lieu lieu;
	
	@ManyToOne 
	Personne deposeur;
	
	@ManyToMany //(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	Collection<Personne> participants;


	public Annonce(){}

	public Annonce(Sport s,Lieu l, Personne p, int nb){
		this.sport = s;
		this.lieu = l;
		this.deposeur = p;
		this.nom = s.toString() + " à " + l.getNom();
		this.participants = new ArrayList<Personne>();
		this.participants.add(p);
		this.setNbMaxParticipant(nb);
	}
	
	public Annonce(Sport s,Lieu l, String n, Personne p, int nb){
		this.sport = s;
		this.lieu = l;
		this.deposeur = p;
		this.nom = n;
		this.participants = new ArrayList<Personne>();
		this.participants.add(p);
		this.setNbMaxParticipant(nb);
	}
	
	public Lieu getLieu() {
		return lieu;
	}
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
//	public Terrain getTerrain() {
//		return terrain;
//	}
//	public void setTerrain(Terrain terrain) {
//		this.terrain = terrain;
//	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	public int getId() {
		return this.id;
	} 
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getIdString() {
		String s = Integer.toString(this.id);
		return s;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Personne> getParticipants() {
		return participants;
	}

	public void setParticipants(Collection<Personne> participants) {
		this.participants = participants;
	}
	
	public Personne getDeposeur() {
		return deposeur;
	}
	public void setDeposeur(Personne deposeur) {
		this.deposeur = deposeur;
	}
	

	/**
	 * Rajoute un participant si possible
	 * @param p le participant
	 * @return true si j'ajout a marché, false sinon
	 */
	public boolean ajouterParticipant(Personne p){
		if (this.participants.size() < this.nbMaxParticipant) {
			this.participants.add(p);
			return true;
		}
		else
			return false;
	}
	public void supprimerParticipant(Personne p){
		this.participants.remove(p);
	}

	public int getNbMaxParticipant() {
		return nbMaxParticipant;
	}

	public void setNbMaxParticipant(int nbMaxParticipant) {
		this.nbMaxParticipant = nbMaxParticipant;
	}

	
	
}
