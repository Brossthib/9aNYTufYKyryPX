package cosport;

import java.util.Collection;

import javax.persistence.*;


@Entity
public class Lieu {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int id;
	String nom;
	
	@OneToMany (mappedBy = "lieu", fetch = FetchType.EAGER)
	Collection <Annonce> annoncesLieu;

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

}
