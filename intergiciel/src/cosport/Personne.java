package cosport;
import java.util.*;



import javax.persistence.*;



@Entity
public class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	private String nom;
	private String prenom;
	private Genre genre;
	//private Lieu lieu ;
	
	@OneToMany (mappedBy = "deposeur", fetch = FetchType.EAGER)
	Collection <Annonce> annonces;
	
	
	public Personne (){
		
	}
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
	
	public int getId() {
		return this.id;
	} 
	
	public void setId(int id) {
		this.id=id;
	}
	public void ajoutAnnoncePersonne(Annonce a){
		this.annonces.add(a);
		
	}

//	public Lieu getLieup() {
//		return lieu;
//	}
//
//	public void setLieup(Lieu lieu) {
//		this.lieu = lieu;
//	}
	
	
	
	
	
}
