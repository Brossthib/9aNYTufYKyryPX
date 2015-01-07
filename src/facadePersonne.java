

import javax.ejb.Singleton;
import javax.persistence.*;


@Singleton
public class facadePersonne {
	@PersistenceContext
	EntityManager em;
	//private Collection<Personne> inscrits = new ArrayList<Personne>();
	
	
	public void ajouterPersonne (Personne p){
		em.persist(p);
	}
	
	public void supprimerPersonne (Personne p){
		em.remove(p);
	}
	
	
	public void associer (int idAnnonce , int idPersonne){
		Annonce a;
		Personne p = em.find(Personne.class, idPersonne);
			a = em.find(Annonce.class, idAnnonce);
			a.setDeposeur(p);
	}
}
