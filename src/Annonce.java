import java.util.*;

import javax.persistence.*;


@Entity
public class Annonce {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	private Lieu lieu;
	private Terrain terrain;
	private Sport sport;
	private Collection<Personne> participants;
	
	@ManyToOne 
	Personne deposeur;
	
	public Annonce(Lieu l, Sport s, Personne p){
		this.sport = s;
		this.lieu = l;
		this.deposeur = p;
		this.participants = new ArrayList<Personne>();
		this.participants.add(p);
	}
	
	public Annonce(Lieu l, Sport s){
		this.sport = s;
		this.lieu = l;
		this.participants = new ArrayList<Personne>();
	}
	
	public Lieu getLieu() {
		return lieu;
	}
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	
	public Personne getDeposeur() {
		return deposeur;
	}
	public void setDeposeur(Personne deposeur) {
		this.deposeur = deposeur;
	}
	
	public void ajouterParticipant(Personne p){
		this.participants.add(p);
	}
	public void supprimerParticipant(Personne p){
		this.participants.remove(p);
	}

	
	
}
