package cosport;
import java.util.*;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class facadeAnnonce {
	@PersistenceContext
	private EntityManager em;

	//private Collection<Annonce> annonces = new ArrayList<Annonce>();
	/*Déplacé vers la facade de connection Session
	 * public void ajouterPersonne (Personne p){
		em.persist(p);
	}*/
	
	public void ajouterAnnonce(Sport s,Lieu l, Personne p, int nb){

		em.persist(l);
		Annonce a = new Annonce(s,l,p,nb);
		em.persist(a);

	}
	
	public void ajouterAnnonce(Sport s,Lieu l, String n, Personne p, int nb){

		em.persist(l);
		Annonce a = new Annonce(s,l,n,p,nb);
		em.persist(a);

	}
	
	public void majAnnonce(Annonce nouvelle, Annonce ancienne) {
		em.remove(ancienne);
		em.persist(nouvelle);
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
	
	/**
	 * Trouve une annonce a partir de son id sous forme de string
	 * @param idS la chaine représentant l'id de l'annonce recherchée
	 * @return l'annonce recherchée si elle existe, null sinon
	 */
	public Annonce trouverAnnonce(String idS) {
		Collection<Annonce> la = this.listerAnnonces();
		for (Annonce a : la) {
			if (a.getIdString().equals(idS)) {
				return a;
			}
		}
		return null;
	}
	
}
