package cosport;


import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Session {
	
	@PersistenceContext
	private EntityManager em;
	
	private boolean connecte = false;
	private Personne utilisateur;
	
	
	/**
	 * Inscrit un nouvel utilsateur (si le nom n'est pas déjà pris)
	 * @param pe le nouvel utilisateur
	 * @return true si l'inscription a fonctionné, false sinon
	 */
	public boolean inscription (Personne pe){
		Collection<Personne> pers = getUtilisateurs();
		for (Personne p : pers) {
			if (p.getPseudo().equals(pe.getPseudo()) || p.getPseudo().equals("empty")) {
				return false;
			}
		}
		em.persist(pe);
		return true; 
	}
	
	/**
	 * Connecte un utilisateur ayant un compte
	 * @param nom 
	 * @param motP
	 * @return true si la connection s'est bien effectuée, false sinon
	 */
	public boolean connection (String pseudo, String motP){
		Collection<Personne> pers = getUtilisateurs();
		for (Personne p : pers) {
			if (p.getPseudo().equals(pseudo) && p.getMdp().equals(motP)) {
				this.connecte = true;
				this.utilisateur = p;
				return true;
			}
		}
		return false;
	}
	
	public void deconnection () {
		this.utilisateur = null;
		this.connecte = false;
	}
	

	public Collection<Personne> getUtilisateurs() {
		Collection<Personne> pers = em.createQuery("select p from Personne p", Personne.class)
				.getResultList();
		return pers;
	}
	
	public boolean isConnecte() {
		return connecte;
	}
	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public Personne getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Personne utilisateur) {
		this.utilisateur = utilisateur;
	}
}
