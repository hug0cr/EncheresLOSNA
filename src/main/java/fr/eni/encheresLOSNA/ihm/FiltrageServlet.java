package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bo.ArticleVendu;

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
		request.getRequestDispatcher("/Controler").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer categorie = Integer.parseInt(request.getParameter("categorie"));
		String motCle = (String) request.getParameter("motCle");
		ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
		
		List<ArticleVendu> lesArticlesParKW = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lesArticlesParCategorie = new ArrayList<ArticleVendu>();
		List<ArticleVendu> articlesToPrint = new ArrayList<ArticleVendu>();
		
		if (categorie == 0 && motCle.equals("")) {
			try {
				articlesToPrint = articleMgr.getArticlesVendusTop50();
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else if (categorie == 0) {
			try {
				articlesToPrint = articleMgr.getArticlesVendusByKW(motCle);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else if (motCle.equals("")) {
			try {
				articlesToPrint = articleMgr.getArticlesVendusByCategorie(categorie);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				lesArticlesParCategorie = articleMgr.getArticlesVendusByCategorie(categorie);
				lesArticlesParKW = articleMgr.getArticlesVendusByKW(motCle);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			for (ArticleVendu article : lesArticlesParCategorie) {
				if (lesArticlesParKW.contains(article)) articlesToPrint.add(article);
			}
		}
		request.setAttribute("lesArticles", articlesToPrint);
		doGet(request, response);
	}

}
