package cosport;
import java.util.*;



import javax.persistence.*;



@Entity
public class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	private String pseudo;
	private String nom;
	private String prenom;
	private Genre genre;
	private String mdp;
	//private Lieu lieu ;
	
	@OneToMany (mappedBy = "deposeur", fetch = FetchType.EAGER)
	Collection <Annonce> annonces;
	

	@OneToMany //(mappedBy = "participants", fetch = FetchType.EAGER)
	Collection <Annonce> participeIci;
	
	
	public Personne (){
		
	}
	public Personne (String p, String nom, String prenom, Genre g, String m){
		this.setPseudo(p);
		this.nom = nom;
		this.prenom = prenom;
		this.setGenre(g);
		this.mdp = m;
		this.annonces = new ArrayList<Annonce>();
		this.participeIci = new ArrayList<Annonce>();
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
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
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
	public void participe(Annonce a) {
		this.participeIci.add(a);
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Collection<Annonce> getAnnonces() {
		return annonces;
	}
	public void setAnnonces(Collection<Annonce> annonces) {
		this.annonces = annonces;
	}
	public Collection<Annonce> getParticipeIci() {
		return participeIci;
	}
	public void setParticipeIci(Collection<Annonce> participeIci) {
		this.participeIci = participeIci;
	}
//	public Lieu getLieup() {
//		return lieu;
//	}
//
//	public void setLieup(Lieu lieu) {
//		this.lieu = lieu;
//	}
	
	
	
	
	
}
