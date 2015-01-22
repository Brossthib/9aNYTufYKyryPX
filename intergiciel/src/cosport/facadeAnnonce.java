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
	
	/*public void ajouterAnnonce(Sport s,Lieu l, Personne p, int nb){
		Annonce a = new Annonce(s,l,p,nb);
		em.persist(a);

	}
	
	public void ajouterAnnonce(Sport s,Lieu l, String n, Personne p, int nb){
		Annonce a = new Annonce(s,l,n,p,nb);
		em.persist(a);

	}*/
	
	public void ajouterAnnonce(Sport s,Lieu l, Personne p, int nb, Terrain t, String d){
		Annonce a = new Annonce(s,l,p,nb, t, d);
		em.persist(a);

	}
	
	public void ajouterAnnonce(Annonce a){
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
	
	
	/***************************************************   LIEU   ***********************************************/

	
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
	

	

	/***************************************************   TERRAIN   ***********************************************/

	
	public void ajouterTerrain(Terrain t){
		em.persist(t);
	}
	public void supprimerTerrain(Terrain t){
		em.remove(t);
	}
	public ArrayList<Terrain> chercherTerrain(int id_lieu, int id_sport){
		Query query = em.createNativeQuery("select * from Terrain where lieu_id=:idl and sport_id=:ids", Annonce.class);
		query.setParameter("idl", id_lieu);
		query.setParameter("ids", id_sport);
		ArrayList<Terrain> t = (ArrayList<Terrain>) query.getResultList();
		return t;
	}


	public boolean existsT(String s) {
		Collection<Terrain> p = listerTerrain();
		for (Terrain t : p) {
			if (t.getNom().equals(s))
				return true;
		}
		return false;
	}
	
	
	public Collection<Terrain> listerTerrain() {
		ArrayList<Terrain> l = (ArrayList<Terrain>) em.createQuery("select l from Terrain l", Terrain.class).getResultList();
		return l;
	}
	
	public Terrain chercherTerrain(String nomTerrain) {
		Query query = em.createNativeQuery("select * from Terrain where nom=:n", Terrain.class);
		query.setParameter("n", nomTerrain);
		Terrain a = (Terrain) query.getSingleResult();
		return a;
	}
	
	public Terrain chercherTerrain(int id_terrain) {
		System.out.println("In chercher t");
		Terrain t = em.find(Terrain.class, id_terrain);
		/*Query query = em.createNativeQuery("select * from Terrain where terrain_id=:idt", Terrain.class);
		query.setParameter("idt", id_terrain);
		Terrain a = (Terrain) query.getSingleResult();*/
		System.out.println("out chcerer t");
		return t;
	}
	
	/*public Terrain valueOft(Lieu lie, Sport s) throws NullNameException {

		Query query = em.createNativeQuery("select * from Terrain where lieu_id=:idl and sport_id=:ids", Annonce.class);
		query.setParameter("idl", id_lieu);
		
		ArrayList<Terrain> res = query.getResultList()
		for (Terrain li : l) {
			if (li.getNom() == null) {
				throw new NullNameException();
			}
			else {
				ArrayList<Sport> lsport = (ArrayList<Sport>)li.getSport();
				for (Sport sp : lsport) {
					if (sp.getId() == s.getId()) {
						ArrayList<Lieu> llieu = 
					}
				}
			return li;
		}
			
			

		}
		Terrain nTerrain = new Terrain(n);
		if (n != null && n!="") {
		em.persist(nTerrain);
		} else {
			throw new NullNameException();
		}
		return (nTerrain);
		
	}*/
	
	/***************************************************   SPORT   ***********************************************/
	
	public Sport valueOf(String n) throws NullNameException {

		ArrayList<Sport> l = (ArrayList<Sport>) em.createNativeQuery("select * from Sport ", Sport.class).getResultList();
		for (Sport li : l) {
			if (li.getNom() == null) {
				throw new NullNameException();
			}
			else if (li.getNom().equals(n)) {
			return li;
		}

		}
		Sport nsport = new Sport(n);
		if (n != null && n!="") {
		em.persist(nsport);
		} else {
			throw new NullNameException();
		}
		return (nsport);
		
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
	
	public Sport chercherSport(int ids) {
		Query query = em.createNativeQuery("select * from Sport where sport_id=:ids", Sport.class);
		query.setParameter("ids", ids);
		Sport a = (Sport) query.getSingleResult();
		return a;
	}
	
	
	public ArrayList<Sport> listerSport() {
		ArrayList<Sport> l = (ArrayList<Sport>) em.createQuery("select l from Sport l", Sport.class).getResultList();
		return l;
	}
	
	public void ajouterSportTerrain(int ids, int idt) {
		System.out.println("In ajouterSport");
		Terrain t = em.find(Terrain.class, idt);
		System.out.println("milieu ajouterSport");
		Sport s = em.find(Sport.class, ids);
		Collection<Sport> ls = t.getSport();
		//t.sports.add(s);
		ls.add(s);
		System.out.println("Out ajouterSport" + t.getSport().iterator().next().getNom());
	}
	
	
		
}
