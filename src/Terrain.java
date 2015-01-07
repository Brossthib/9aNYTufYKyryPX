import java.util.*;

import javax.persistence.*;

@Entity
public class Terrain {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	private Lieu lieu;
	//private Set<Sport> sports; 
	private Sport sport;
	private TypeTerrain type;
	
	public Terrain(Lieu lieu, Sport sport){
		this.lieu = lieu;
		this.sport = sport;
		//this.sports = new TreeSet<Sport>(); 
	}
	public Lieu getLieu() {
		return lieu;
	}
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	public void setSport (Sport s){
		this.sport = s;
	}
	public Sport getSport(){
		return this.sport;
	}
	public TypeTerrain getType() {
		return type;
	}
	public void setType(TypeTerrain type) {
		this.type = type;
	}
//	public boolean contientSport(Sport s){
//		return this.sports.contains(s);
//	}
	
}
