package cosport;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Sport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int id;
	
	private String nom;
	
	@OneToMany (mappedBy = "sport", fetch = FetchType.EAGER)
	Collection<Annonce> ann;
	
	@ManyToMany (mappedBy = "sports")
	@LazyCollection(LazyCollectionOption.FALSE)
	Collection<Terrain> terr;
	
	public Sport() {}
	
	public Sport (String n){
		this.setNom(n);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String name) {
		this.nom = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
