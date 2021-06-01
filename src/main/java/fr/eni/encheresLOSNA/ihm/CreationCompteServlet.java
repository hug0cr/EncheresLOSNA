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
 * Servlet implementation class CreationCompte
 */
@WebServlet("/CreationCompteServlet")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		UtilisateurManager userMgr = UtilisateurManager.getInstance();
		RequestDispatcher compteOK = request.getRequestDispatcher("./listeEncheres");
		RequestDispatcher conpteNotOK = request.getRequestDispatcher("./creerCompte?type=error");
		
		
		if (!motDePasse.equals(confirmation)) {
		} else {
			Utilisateur user = new Utilisateur(pseudo, nom, prenom, mail, phone, rue, codePostal, ville, motDePasse, 0, false);
			try {
				userMgr.addUtilisateur(user);
			} catch (BLLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		compteOK.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
}
