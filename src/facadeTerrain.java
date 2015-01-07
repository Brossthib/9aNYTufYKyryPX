import java.util.*;

import javax.ejb.Singleton;
import javax.persistence.*;



@Singleton
public class facadeTerrain {
	@PersistenceContext
	EntityManager em;
	//private Collection<Terrain> terrains = new ArrayList<Terrain>();
	
	public void ajouterTerrain(Terrain t){
		em.persist(t);
	}
	public void supprimerTerrain(Terrain t){
		em.remove(t);
	}
	public ArrayList<Terrain> chercherTerrain(Lieu l, Sport s){
//		ArrayList<Terrain> resultats = new ArrayList<Terrain>();
//		for(Terrain t : terrains){
//			if (t.getLieu() == l && t.contientSport(s)){
//				resultats.add(t);
//			}
//		}
//		return resultats;
		ArrayList<Terrain> t = (ArrayList<Terrain>) em.createQuery("select * from Terrain where lieu=l and sport = s", Terrain.class).getResultList();
		return t;
	}
}
