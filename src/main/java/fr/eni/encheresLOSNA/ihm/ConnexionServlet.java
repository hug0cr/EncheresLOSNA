package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.UtilisateurManager;
import fr.eni.encheresLOSNA.bo.Utilisateur;
import fr.eni.encheresLOSNA.ihm.features.Mail;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/seConnecter");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager userMgr = UtilisateurManager.getInstance();
		Utilisateur user = null;
		String mailOubli = (String) request.getParameter("mailOubli");
		if (mailOubli != null) {
			System.out.println("Envoi de mail");
			doGet(request, response);
			
		} else {
			System.out.println("On vérifie la connexion");
			
			String id = (String) request.getParameter("mail");
			String mdp = (String) request.getParameter("mdp");
			try {
				user = userMgr.getUtilisateurByEmail(id);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageeErreur", "Adresse e-mail inconnue");
				request.getRequestDispatcher("./seConnecter").forward(request, response);
			}
			
			if (mdp != user.getMotDePasse()) {
				request.setAttribute("messageeErreur", "Mot de passe incorrect");
				request.getRequestDispatcher("./seConnecter").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);
				if (request.getParameter("seSouvenirDeMoi") != null) session.setAttribute("seSouvenirDeMoi", true);
				else session.setAttribute("seSouvenirDeMoi", false);
			}
		}
		request.getRequestDispatcher("./listeEncheres").forward(request, response);
		
	}

}
