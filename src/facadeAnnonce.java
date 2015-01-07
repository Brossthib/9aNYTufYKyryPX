import java.util.*;


import javax.ejb.Singleton;
import javax.persistence.*;


@Singleton
public class facadeAnnonce {
	@PersistenceContext
	EntityManager em;

	//private Collection<Annonce> annonces = new ArrayList<Annonce>();
	
	public void ajouterAnnonce(Lieu l, Sport s){
		//this.annonces.add(a);
		Annonce a = new Annonce(l,s);
		em.persist(a);
	}
	public void supprimerAnnonce(Annonce a){
		em.remove(a);
	}
	
	public ArrayList<Annonce> chercherAnnonces(Lieu l, Sport s){
//		ArrayList<Annonce> resultats = new ArrayList<Annonce>();
//		for(Annonce a : annonces){
//			if (a.getLieu() == l && a.getSport() == s){
//				resultats.add(a);
//			}
//		}
//		return resultats;
		ArrayList<Annonce> a = (ArrayList<Annonce>) em.createQuery("select * from Annonce where lieu=l and sport=s", Annonce.class).getResultList();
		return a;
	}
}
