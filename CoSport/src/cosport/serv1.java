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
		String sop = request.getParameter("op");
		
		if(sop.equals("Deposer annonce")){
			//reflechir à comment obtenir la personne connectée pour lui associer l'annonce
			String sport = request.getParameter("sport");
			String lieu = request.getParameter("lieu");
			//convertir la String sport en l'objet Sport
			Sport s = Sport.valueOf(sport);
			Lieu l = fl.trouverLieu(lieu);
			//Lieu l=new Lieu(lieu);
			fa.ajouterAnnonce(s,l);
			//response.getWriter().println("<html><body>" + "resultats" + "</body></html>");
			this.getServletContext().getRequestDispatcher("/resultats.html").forward(request, response);
		}
			
		if(sop.equals("lister")){
			Collection<Annonce> annonces = fa.listerAnnonces();
			request.setAttribute("annonces", annonces);
			this.getServletContext().getRequestDispatcher("/lister.jsp").forward(request, response);
		}

		
}
	}
