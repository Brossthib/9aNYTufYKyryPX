package cosport;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Terrain {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	
	private String nom;
	
	@OneToMany (mappedBy = "terrain", fetch = FetchType.EAGER)
	Collection<Annonce> annonces; 
	
	@ManyToOne
	private Lieu lieuTerrain;
	
	@ManyToMany //(mappedBy = "terr")
	@LazyCollection(LazyCollectionOption.FALSE)
	Collection<Sport> sports;
	
	private Boolean isPrivate;
	
	public Terrain() {}
	
	public Terrain(Lieu lieu, String nom, Boolean isP){
		this.lieuTerrain = lieu;
		this.nom = nom;
		this.sports = new ArrayList<Sport>(); 
		this.isPrivate = isP;
		//this.sports = sport;
	}
	
	
	public Terrain(Lieu lieu, Sport sport){
		this.lieuTerrain = lieu;
		this.nom = this.lieuTerrain.getNom();
		this.sports = new ArrayList<Sport>(); 
		//this.sports = sport;

	}
	public Lieu getLieu() {
		return lieuTerrain;
	}
	public void setLieu(Lieu lieu) {
		this.lieuTerrain = lieu;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Sport> getSport() {
		return this.sports;
	}
	
	public void ajouterSport(Sport s) {
		this.sports.add(s);
	}
	
	public void addAnnonce(Annonce a) {
		this.annonces.add(a);
	}

	
//	public boolean contientSport(Sport s){
//		return this.sports.contains(s);
//	}
	
}