package cosport;


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
		ArrayList<Lieu> l = (ArrayList<Lieu>) em.createQuery("select l from Lieu l", Lieu.class).getResultList();
		return l;
	}
	
	public Lieu trouverLieu(String n){
			ArrayList<Lieu> l = (ArrayList<Lieu>) em.createNativeQuery("select * from Lieu where nom='n' ", Lieu.class).getResultList();
			if(!(l.isEmpty())){System.out.println("sfhu" + l.get(0).getNom());return l.get(0);}
			else {return (new Lieu(n));}
	}
	
}
