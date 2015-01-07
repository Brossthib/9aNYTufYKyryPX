import java.util.*;

import javax.persistence.*;


@Entity
public class Personne {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	private String nom;
	private String prenom;
	private Genre genre;
	//private Lieu lieu ;
	@OneToMany (mappedBy = "deposeur")
	Collection <Annonce> annonces;
	
	public Personne (String nom, String prenom, Genre g){
		this.nom = nom;
		this.prenom = prenom;
		this.setGenre(g);
		this.annonces = new ArrayList<Annonce>();
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

//	public Lieu getLieup() {
//		return lieu;
//	}
//
//	public void setLieup(Lieu lieu) {
//		this.lieu = lieu;
//	}
	
	
	
	
	
}
