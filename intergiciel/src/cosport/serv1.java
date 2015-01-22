package cosport;


import java.io.IOException;


import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cosport.facadeAnnonce;


/**
 * Servlet implementation class serv1
 */
@WebServlet("/serv1")
public class serv1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       @EJB 
       private facadeAnnonce fa;
       
       @EJB
       private Session session;
       
       //@EJB
       //private facadeLieu fl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serv1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//L'administateur est le seul a pouvoir ajouter des terrains
		Personne padmin = new Personne("admin", "coucou", "salut", Genre.Masculin, "a");
		//Represente une personne vide, mais <> de null pour eviter les exceptions
		Personne loggedOut = new Personne("out", "", "", Genre.Masculin, "");
		session.inscription(padmin);
		
		if (session.isConnecte()) {
			Personne p = session.getUtilisateur();
			request.setAttribute("User", p);
		}
		else {
			request.setAttribute("User", null);
		}
		
		request.setAttribute("Lterrain", fa.listerTerrain());
		
		String sop = request.getParameter("op");
		
		
		if(sop.equals("deposer")){
			request.setAttribute("success", 1);
			this.getServletContext().getRequestDispatcher("/deposer.jsp").forward(request, response);
		}
		
		if(sop.equals("Deposer annonce") || sop.equals("DeposerAnnonce")){
			//On recupere les paramètres
			String sport = request.getParameter("sport");
			String lieu = request.getParameter("lieu");
			String nbp = request.getParameter("nb");
			String idtstring = request.getParameter("terrain");
			String sjour = request.getParameter("jour");
			String smois = request.getParameter("mois");
			String sannee = request.getParameter("annee");
			String sheure = request.getParameter("heure");
			String sminute = request.getParameter("minutes");

			

			//Si le sport entré existe
			try {
				
				Integer jour = Integer.parseInt(sjour);
				Integer mois = Integer.parseInt(smois);
				Integer annee = Integer.parseInt(sannee);
				Integer heure = Integer.parseInt(sheure);
				Integer minute = Integer.parseInt(sminute);
				Integer nb = Integer.parseInt(nbp);
				Integer idt = Integer.parseInt(idtstring);
				
				Date d = new Date(annee,mois,jour,heure,minute);

				//convertir la String sport en l'objet Sport
				Sport s = fa.valueOf(sport);
				
				if(lieu.equals("") || nb < 2) {
					request.setAttribute("success", 0);
					this.getServletContext().getRequestDispatcher("/deposer.jsp").forward(request, response);
				}
				else {
						//On doit être connecté pour pouvoir déposer une annonce
					if (session.isConnecte()) {
		
						Lieu l = fa.trouverLieu(lieu);
						Terrain t = fa.chercherTerrain(idt);
						Personne p = session.getUtilisateur();
						
						fa.ajouterAnnonce(s,l,p,nb,t,d.toString());
						request.setAttribute("annonces", fa.listerAnnonces());
						this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
						
					}
					else {
						this.getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
					}
				}
			//Si le sport entré n'existe pas
			}catch (Exception e) {
				request.setAttribute("success", 0);
				this.getServletContext().getRequestDispatcher("/deposer.jsp").forward(request, response);
			}

		}
			
		if(sop.equals("lister")){
			Collection<Annonce> annonces = fa.listerAnnonces();
			request.setAttribute("annonces", annonces);
			this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
		}
		
		if(sop.equals("afficher annonces")|| sop.equals("afficherAnnonce")){
			String ann = request.getParameter("annonce");
			if (fa.trouverAnnonce(ann)==null) {
				request.setAttribute("status", 0);
				this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
			}
			else{
			request.setAttribute("status", 1);
			request.setAttribute("annonce", fa.trouverAnnonce(ann));
			this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
			}
		}
		
		if (sop.equals("admin")) {
			Personne p1 = new Personne("admin", "coucou", "salut", Genre.Masculin, "a");
			Personne p2 = new Personne("user", "ave", "cesar", Genre.Masculin, "b");
			session.inscription(p1);
			session.inscription(p2);
			session.connection("admin", "a");
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		/*if (sop.equals("annonce1")) {
			fa.ajouterAnnonce(Sport.Tennis, new Lieu("Paris"), session.getUtilisateur(),2);
			fa.ajouterAnnonce(Sport.Foot, new Lieu("Toulouse"), session.getUtilisateur(),24);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}*/
		
		if (sop.equals("connection")) {
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("motP");
			if (session.connection(pseudo, mdp)) {
				request.setAttribute("User", session.getUtilisateur());
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else {
				//On indique que ce n'est pas le premier essai
				request.setAttribute("success", 0);
				this.getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
			}
		}
		
		if (sop.equals("inscrire")) {
			request.setAttribute("status", 1);
			this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
		}
		
		if (sop.equals("inscription")) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String mdp = request.getParameter("motP");
			String mdp2 = request.getParameter("motP2");
			String prenom = request.getParameter("prenom");
			String g = request.getParameter("genre");
			try {
			Genre genre = Genre.valueOf(g);
			Personne p = new Personne(pseudo, nom, prenom, genre, mdp);
			if (session.inscription(p) && mdp.equals(mdp2)) {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(mdp.equals(mdp2)){
				request.setAttribute("status", 2);
				this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
			}
			else {
				request.setAttribute("status", 0);
				this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
			}
			} catch (Exception e) {
				request.setAttribute("status", 3);
				this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
			}
		}
		
		if (sop.equals("deconnecter")) {
			session.deconnection();
			request.setAttribute("User", null);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		if (sop.equals("participer")) {
			String idAnnonce = request.getParameter("annonce");
			int ida = Integer.parseInt(idAnnonce);
			if (fa.ajouterParticipant(session.getUtilisateur().getId(),ida)) {
				Annonce ann = fa.trouverAnnonce(idAnnonce);
				request.setAttribute("annonce", ann);
				request.setAttribute("status", 1);
				this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
			}
			else
				request.setAttribute("annonce", idAnnonce);
				request.setAttribute("status", 0);
				this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
		}
		
		if (sop.equals("mesAnnonces")) {
			if (session.isConnecte()){
				Personne p = session.getUtilisateur();
				Collection<Annonce> ann = p.getParticipeIci();
				System.out.println("Nombre d'annonces :" + ann.size());
				request.setAttribute("annonces", ann);
				this.getServletContext().getRequestDispatcher("/listerPerso.jsp").forward(request, response);
			}
			else
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

		}
		
		if (sop.equals("index")) {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}

		if (sop.equals("connectionjsp")) {
			//On indique qu'il s'agit du premier essai
			request.setAttribute("success", 1);
			this.getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
		}
		
		if (sop.equals("profil")) {	
			this.getServletContext().getRequestDispatcher("/utilisateur.jsp").forward(request, response);
		}
		
		if (sop.equals("modifier")) {
			request.setAttribute("status", 1);
			this.getServletContext().getRequestDispatcher("/modifier.jsp").forward(request, response);
		}
		
		if (sop.equals("modifierProfil")) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String mdpa = request.getParameter("AmotP");
			String mdp1 = request.getParameter("motP1");
			String mdp2 = request.getParameter("motP2");
			String prenom = request.getParameter("prenom");
			String g = request.getParameter("genre");
			Genre genre = Genre.valueOf(g);
			Personne pers = session.getUtilisateur();
			if (mdp1.equals("") && mdp2.equals("") && mdpa.equals("")) {
				Personne newPers = new Personne(pseudo,nom,prenom,genre,pers.getMdp());
				session.modifierUtilisateur(newPers);
				request.setAttribute("User", newPers);
				this.getServletContext().getRequestDispatcher("/utilisateur.jsp").forward(request, response);
		}
			else if (mdp1.equals(mdp2) && pers.getMdp().equals(mdpa)) {
				Personne newPers = new Personne(pseudo,nom,prenom,genre,mdp1);
				session.modifierUtilisateur(newPers);
				request.setAttribute("User", newPers);
				this.getServletContext().getRequestDispatcher("/utilisateur.jsp").forward(request, response);
			}
			else if (mdp1.equals(mdp2)) {
				request.setAttribute("status", 0);
				this.getServletContext().getRequestDispatcher("/modifier.jsp").forward(request, response);
			}
			else {
				request.setAttribute("status", 2);
				this.getServletContext().getRequestDispatcher("/modifier.jsp").forward(request, response);
			}
		}
		
		if (sop.equals("rechercher")) {
			String l = request.getParameter("motLieu");
			String s = request.getParameter("motSport");
			
			try {
			fa.valueOf(s);
			
			Lieu ll = fa.trouverLieu(l);			
			
			int idl = ll.getId();
			
			Collection<Annonce> resultats = fa.chercherAnnonces(idl);
			resultats = fa.chercherAnnoncesSport(resultats, s);
			request.setAttribute("annonces", resultats);
			this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
			
			} catch (Exception e) {
				request.setAttribute("annonces", new ArrayList<Annonce>());
				this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
			}
		}
		
		if (sop.equals("payer")) {
			this.getServletContext().getRequestDispatcher("/payement.jsp").forward(request, response);
		}
		
		if (sop.equals("terrainajout")) {
			request.setAttribute("status", 1);
			this.getServletContext().getRequestDispatcher("/ajouterTerrain.jsp").forward(request, response);
		}
		
		if (sop.equals("ajTerrain")) {
			try {
				String n = request.getParameter("nom");
				String l = request.getParameter("lieu");
				String s = request.getParameter("sport");
				String p = request.getParameter("prive");
				
				Terrain t =	new Terrain(fa.trouverLieu(l), n, Boolean.valueOf(p));
				Sport sp = fa.valueOf(s);
				fa.ajouterTerrain(t);
				fa.ajouterSportTerrain(sp.getId(), t.getId());
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("Terrain mal crée");
			}
			
		}
	}
}
