package cosport;


import java.util.Collection;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
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
			if (p.getPseudo().equals(pe.getPseudo())) {
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
		if  (this.isConnecte()) {
			return false;
		}
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
		Personne p = em.find(Personne.class, utilisateur.getId());
		return p;
	}

	public void setUtilisateur(Personne utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public boolean exists(String n) {
		Collection<Personne> p = getUtilisateurs();
		for (Personne pers : p) {
			if (pers.getPseudo().equals(n))
				return true;
		}
		return false;
	}

	
	public void modifierUtilisateur(Personne p) {
		Personne pMod = em.find(Personne.class, utilisateur.getId());
		pMod.setPseudo(p.getPseudo());
		pMod.setPrenom(p.getPrenom());
		pMod.setNom(p.getNom());
		pMod.setGenre(p.getGenre());
		pMod.setMdp(p.getMdp());

	}
}
