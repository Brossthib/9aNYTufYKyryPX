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
       
       @EJB
       private facadeLieu fl;
       
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
		
		if (session.isConnecte()) {
			Personne p = session.getUtilisateur();
			request.setAttribute("User", p);
		}
		else {
			request.setAttribute("User", null);
		}
		
		String sop = request.getParameter("op");
		
		if(sop.equals("Deposer annonce")){
			//reflechir à comment obtenir la personne connectée pour lui associer l'annonce
			String sport = request.getParameter("sport");
			String lieu = request.getParameter("lieu");
			String nbp = request.getParameter("nb");
			System.out.println(nbp);
			
			//Si le sport entré existe
			try {

				//convertir la String sport en l'objet Sport
				Sport s = Sport.valueOf(sport);
				
				if(lieu.equals("")) {
					this.getServletContext().getRequestDispatcher("/failedRequest.html").forward(request, response);
				}
				else {
						//On doit être connecté pour pouvoir déposer une annonce
					if (session.isConnecte()) {
		
						Lieu l = fl.trouverLieu(lieu);
						int nb = Integer.parseInt(nbp);
						Personne p = session.getUtilisateur();
						fa.ajouterAnnonce(s,l,p,nb);
						//this.getServletContext().getRequestDispatcher("/serv1?op=lister").forward(request, response);
						this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
					}
					else {
						this.getServletContext().getRequestDispatcher("/connection.html").forward(request, response);
					}
				}
			//Si le sport entré n'existe pas
			}catch (Exception e) {
				this.getServletContext().getRequestDispatcher("/failedRequest.html").forward(request, response);
			}

		}
			
		if(sop.equals("lister")){
			Collection<Annonce> annonces = fa.listerAnnonces();
			request.setAttribute("annonces", annonces);
			this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
		}
		
		if(sop.equals("afficher annonce")){
			String ann = request.getParameter("annonce");
			if (fa.trouverAnnonce(ann)==null)
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
			else{
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
			this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		
		if (sop.equals("annonce1")) {
			fa.ajouterAnnonce(Sport.Tennis, new Lieu("Paris"), session.getUtilisateur(),2);
			fa.ajouterAnnonce(Sport.Foot, new Lieu("Toulouse"), session.getUtilisateur(),24);
			this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		
		if (sop.equals("connection")) {
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("motP");
			if (session.connection(pseudo, mdp)) {
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
			}
			else {
				this.getServletContext().getRequestDispatcher("/connection.html").forward(request, response);
			}
		}
		
		if (sop.equals("inscription")) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String mdp = request.getParameter("motP");
			String prenom = request.getParameter("prenom");
			String g = request.getParameter("genre");
			Genre genre = Genre.valueOf(g);
			Personne p = new Personne(pseudo, nom, prenom, genre, mdp);
			if (session.inscription(p)) {
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
			}
			else {
				this.getServletContext().getRequestDispatcher("/inscription.html").forward(request, response);
			}
		}
		
		if (sop.equals("deconnecter")) {
			session.deconnection();
			this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		
		if (sop.equals("participer")) {
			String idAnnonce = request.getParameter("annonce");
			Annonce ann = fa.trouverAnnonce(idAnnonce);
			if (ann.ajouterParticipant(session.getUtilisateur())) {
				fa.majAnnonce(ann, fa.trouverAnnonce(idAnnonce));
				request.setAttribute("annonce", ann);
				this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
			}
			else
				this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		
		if (sop.equals("mesAnnonces")) {
			this.getServletContext().getRequestDispatcher("/listerPerso.jsp").forward(request, response);
		}

		
	}
}
