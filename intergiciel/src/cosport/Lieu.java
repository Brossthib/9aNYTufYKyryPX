package cosport;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Lieu {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int id;
	String nom;
	
	@OneToMany (mappedBy = "lieu", fetch = FetchType.EAGER)
	Collection <Annonce> annoncesLieu;
	
	@OneToMany (mappedBy = "lieuTerrain")
	@LazyCollection(LazyCollectionOption.FALSE)
	Collection<Terrain> terrains;

	public Lieu(){}
	
	public Lieu(String nom){
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return this.id;
	} 
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void ajoutAnnonceLieu(Annonce a){
		this.annoncesLieu.add(a);
		
	}

	public Collection<Terrain> getTerrains() {
		return terrains;
	}

	public void setTerrains(Collection<Terrain> terrains) {
		this.terrains = terrains;
	}
	
	

}
