import javax.persistence.*;


@Entity
public class Lieu {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	String nom;
	
	public Lieu(String nom){
		this.nom = nom;
	}
}
