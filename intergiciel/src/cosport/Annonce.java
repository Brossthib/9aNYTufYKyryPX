package cosport;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Annonce {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	//private Lieu lieu;
	//private Terrain terrain;
	private Sport sport;
	//private Collection<Personne> participants;
	
	@ManyToOne
	Lieu lieu;
	
	@ManyToOne 
	Personne deposeur;
	
	//@OneToMany
	//Collection<Personne> participants;
	
	public Annonce(){}

	public Annonce(Sport s,Lieu l){
		this.sport = s;
		this.lieu = l;
		//this.participants = new ArrayList<Personne>();
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
	
	public Personne getDeposeur() {
		return deposeur;
	}
	public void setDeposeur(Personne deposeur) {
		this.deposeur = deposeur;
	}
	
//	public void ajouterParticipant(Personne p){
//		this.participants.add(p);
//	}
//	public void supprimerParticipant(Personne p){
//		this.participants.remove(p);
//	}

	
	
}
