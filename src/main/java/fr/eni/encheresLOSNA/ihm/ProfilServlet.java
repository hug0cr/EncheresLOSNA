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

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager userMgr = UtilisateurManager.getInstance();
		HttpSession session = request.getSession(false);
		if (request.getParameter("type") != null && request.getParameter("type").equals("suppr")) {
			try {
				userMgr.removeUtilisateur((Utilisateur) session.getAttribute("user"));
				request.setAttribute("message", "Profil supprimé avec succès");
				session.invalidate();
				request.getRequestDispatcher("./Controler").forward(request, response);
			} catch (BLLException e) {
				request.setAttribute("message", "La suppression du profil a échoué");
				request.getRequestDispatcher("./gererProfil").forward(request, response);
				e.printStackTrace();
			}
		}
		if (request.getParameter("user") != null) {
			Integer noUtilisateur = Integer.parseInt(request.getParameter("user"));
			Utilisateur userSession = (Utilisateur) session.getAttribute("user");
			Utilisateur user;
			if (noUtilisateur == userSession.getNoUtilisateur()) {
				request.getRequestDispatcher("./pageProfil?type=monProfil").forward(request, response);
			}
			try {
				user = userMgr.getUtilisateurById(noUtilisateur);
				request.setAttribute("user", user);
				request.getRequestDispatcher("./pageProfil").forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = (String) request.getParameter("type");
		UtilisateurManager userMgr = UtilisateurManager.getInstance();
		RequestDispatcher home = request.getRequestDispatcher("./Controler");
		RequestDispatcher pageProfil = request.getRequestDispatcher("./pageProfil");
		RequestDispatcher gererProfil = request.getRequestDispatcher("./gererProfil");
		Utilisateur user = createUser(request);
		request.setAttribute("error", false);
		HttpSession session = request.getSession();
		switch (type) {
		case "modif":
			Utilisateur userSession = (Utilisateur) session.getAttribute("user");
			user.setMotDePasse(userSession.getMotDePasse());
			user.setNoUtilisateur(userSession.getNoUtilisateur());
			try {
				userMgr.updateUtilisateur(user);
				session.setAttribute("user", user);
				pageProfil.forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("message", "La modification du profil a échoué");
				gererProfil.forward(request, response);
			}
			break;
		case "crea":
			try {
				userMgr.addUtilisateur(user);
				session.setMaxInactiveInterval(5 * 60);
				session.setAttribute("user", user);
				home.forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("message", "La création du profil a échoué");
				gererProfil.forward(request, response);
			}
			break;
		}
	}
	
	private Utilisateur createUser(HttpServletRequest request) throws ServletException, IOException {
		Utilisateur user = null;
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String pseudo = request.getParameter("pseudo");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String codePostal = request.getParameter("codePostal");
		
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		if (!motDePasse.equals(confirmation)) {
			request.setAttribute("confirmationMDP", false);
		} else {
			user = new Utilisateur(pseudo, nom, prenom, mail, phone, rue, codePostal, ville, motDePasse, 0, false);
			request.setAttribute("confirmationMDP", true);
		}
		return user;
	}
}
