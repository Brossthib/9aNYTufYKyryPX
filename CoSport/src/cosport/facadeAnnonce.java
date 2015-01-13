package cosport;
import java.util.*;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class facadeAnnonce {
	@PersistenceContext
	private EntityManager em;

	//private Collection<Annonce> annonces = new ArrayList<Annonce>();
	public void ajouterPersonne (Personne p){
		em.persist(p);
	}
	
	public void ajouterAnnonce(Sport s,Lieu l){

		em.persist(l);
		Annonce a = new Annonce(s,l);
		a.setLieu(l);
		em.persist(a);

	}
/*	public void supprimerAnnonce(Annonce a){
		em.remove(a);
	}
	
	public ArrayList<Annonce> chercherAnnonces(Sport s){
//		ArrayList<Annonce> resultats = new ArrayList<Annonce>();
//		for(Annonce a : annonces){
//			if (a.getLieu() == l && a.getSport() == s){
//				resultats.add(a);
//			}
//		}
//		return resultats;
		ArrayList<Annonce> a = (ArrayList<Annonce>) em.createQuery("select * from Annonce where sport=s", Annonce.class).getResultList();
		return a;
	}*/
	
	
	public Collection<Annonce> listerAnnonces(){
		Collection<Annonce> la = em.createQuery("select a from Annonce a", Annonce.class)
				.getResultList();
		return la;
	}
	
}
