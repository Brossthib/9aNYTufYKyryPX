


import java.util.ArrayList;

import javax.ejb.Singleton;
import javax.persistence.*;


@Singleton
public class facadeLieu {
	@PersistenceContext
	EntityManager em;
	
	public void ajouterLieu(String nom){
		Lieu l = new Lieu(nom);
		em.persist(l);
	}
	
	public void supprimerLieu(Lieu l){
		em.remove(l);
	}
	
	public ArrayList<Lieu> listerLieux(){
		ArrayList<Lieu> l = (ArrayList<Lieu>) em.createQuery("select * from Lieu", Lieu.class).getResultList();
		return l;
	}
	
	public Lieu trouverLieu(String n){
			ArrayList<Lieu> l = (ArrayList<Lieu>) em.createQuery("select * from Lieu where Lieu.nom = n", Lieu.class).getResultList();
			return l.get(0);
	}
}
