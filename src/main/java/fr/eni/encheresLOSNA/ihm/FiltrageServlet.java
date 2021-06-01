package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresLOSNA.bll.CategorieManager;
import fr.eni.encheresLOSNA.bll.EnchereManager;
import fr.eni.encheresLOSNA.bo.Enchere;

/**
 * Servlet implementation class FiltrageServlet
 */
@WebServlet("/FiltrageServlet")
public class FiltrageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/listeEncheres");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categorie = (String) request.getAttribute("categorie");
		String motCle = (String) request.getAttribute("nomArticle");
		EnchereManager enchereMgr = EnchereManager.getInstance();
		
		List<Enchere> lesEncheresByKW = enchereMgr.getEncheresByKW(motCle);
		List<Enchere> lesEncheresByCategorie = enchereMgr.getEncheresByCategorie(categorie);
		Enchere[] enchereToPrint = new Enchere[lesEncheresByCategorie.size()];
		int i = 0;
		
		for (Enchere enchere : lesEncheresByCategorie) {
			if (lesEncheresByKW.contains(enchere)) {
				enchereToPrint[i] = enchere;
				i++;
			}
		}
		request.setAttribute("lesEncheres", enchereToPrint);
		doGet(request, response);
	}

}
