package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		RequestDispatcher rd = request.getRequestDispatcher("./listeEncheres");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = (String) request.getParameter("type");
		UtilisateurManager userMgr = UtilisateurManager.getInstance();
		RequestDispatcher home = request.getRequestDispatcher("./listeEncheres");
		RequestDispatcher creaProfil = request.getRequestDispatcher("./creerProfil");
		RequestDispatcher profilVendeur = request.getRequestDispatcher("./profilVendeur");
		RequestDispatcher modifierProfil = request.getRequestDispatcher("./modifierProfil");
		Utilisateur user = createUser(request);
		request.setAttribute("error", false);
		
		if (type.equals("modif")) {
			try {
				userMgr.updateUtilisateur(user);
				profilVendeur.forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("error", true);
				modifierProfil.forward(request, response);
			}
		}
		if (type.equals("crea")) {
			try {
				userMgr.addUtilisateur(user);
				home.forward(request, response);
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("error", true);
				creaProfil.forward(request, response);
			}
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
