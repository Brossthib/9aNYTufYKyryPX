package cosport;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class facadeAnnonce {
	@PersistenceContext
	private EntityManager em;

	//private Collection<Annonce> annonces = new ArrayList<Annonce>();
	/*Déplacé vers la facade de connection Session
	 * public void ajouterPersonne (Personne p){
		em.persist(p);
	}*/
	
	public void ajouterAnnonce(Sport s,Lieu l, Personne p, int nb){
		Annonce a = new Annonce(s,l,p,nb);
		em.persist(a);

	}
	
	public void ajouterAnnonce(Sport s,Lieu l, String n, Personne p, int nb){
		Annonce a = new Annonce(s,l,n,p,nb);
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
		Collection<Annonce> la = em.createNativeQuery("select * from Annonce", Annonce.class)
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
	
	/**
	 * Rajoute un participant si possible
	 * @param p le participant
	 * @return true si j'ajout a marché, false sinon
	 */
	public boolean ajouterParticipant(int idp, int ida){
		Personne p = em.find(Personne.class, idp);
		Annonce a = em.find(Annonce.class, ida);
		Collection<Personne> part = a.getParticipants();
		if (a.participants.size() < a.getNbMaxParticipant() && !(part.contains(p))) {
			a.participants.add(p);
			return true;
		}
		else
			return false;
	}
	
	public Collection<Annonce> chercherAnnonces(int id_lieu) {
		Query query = em.createNativeQuery("select * from Annonce where lieu_id=:idl", Annonce.class);
		query.setParameter("idl", id_lieu);
		Collection<Annonce> a = (Collection<Annonce>) query.getResultList();
		//Collection <Annonce> a = em.createNativeQuery("select * from Annonce where lieu_id=:idl", Annonce.class).getResultList();
		/*
		for (Annonce an : a) {
			if (an.getLieu().getId() == id_lieu) {
			return an;
		}
		}*/
		return a;
	}
	
	
	public Lieu trouverLieu(String n) throws NullNameException {

		ArrayList<Lieu> l = (ArrayList<Lieu>) em.createNativeQuery("select * from Lieu ", Lieu.class).getResultList();
		for (Lieu li : l) {
			if (li.getNom() == null) {
				throw new NullNameException();
			}
			else if (li.getNom().equals(n)) {
			return li;
		}

		}
		Lieu nlieu = new Lieu(n);
		if (n != null && n!="") {
		em.persist(nlieu);
		} else {
			throw new NullNameException();
		}
		return (nlieu);
}
	
	public void ajouterLieu(String nom){
		Lieu l = new Lieu(nom);
		em.persist(l);
	}
	
	public void supprimerLieu(Lieu l){
		em.remove(l);
	}
	
	public ArrayList<Lieu> listerLieux(){
		ArrayList<Lieu> l = (ArrayList<Lieu>) em.createQuery("select l from Lieu l", Lieu.class).getResultList();
		return l;
	}
	
	
	public ArrayList<Annonce> chercherAnnoncesSport(Collection<Annonce> ann, String s) {
		ArrayList<Annonce> res = new ArrayList<Annonce>();
		for (Annonce a : ann) {
			if (a.getSport().toString().equals(s)) {
				res.add(a);
			}
		}
		return res;
	}

		
}
