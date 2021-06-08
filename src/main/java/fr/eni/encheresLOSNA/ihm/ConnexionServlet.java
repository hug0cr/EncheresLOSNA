package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.UtilisateurManager;
import fr.eni.encheresLOSNA.bo.Utilisateur;

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
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("loginLOSNA")) {
				request.setAttribute("login", c.getValue());
			}
		}
		request.getRequestDispatcher("./seConnecter").forward(request, response);
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
			String id = (String) request.getParameter("mail");
			String mdp = (String) request.getParameter("mdp");
			try {
				user = userMgr.getUtilisateurByEmail(id);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageeErreur", "Erreur Base de données");
				request.getRequestDispatcher("./seConnecter").forward(request, response);
			}
			
			if (user == null) {
				request.setAttribute("messageeErreur", "Utilisateur inconnu");
				request.getRequestDispatcher("./seConnecter").forward(request, response);
			}
			
			if (!mdp.equals(user.getMotDePasse())) {
				request.setAttribute("messageeErreur", "Mot de passe incorrect");
				request.getRequestDispatcher("./seConnecter").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(5 * 60);
				session.setAttribute("user", user);
				if (request.getParameter("seSouvenirDeMoi") != null) {
					Cookie cookie = new Cookie("loginLOSNA", user.getEmail());
					cookie.setHttpOnly(true);
					cookie.setMaxAge(3600*24*60);
					response.addCookie(cookie);
				}
				request.getRequestDispatcher("./Controler").forward(request, response);
			}
		}
		
	}

}
