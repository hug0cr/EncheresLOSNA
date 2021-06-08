package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.EnchereManager;
import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/EncherirServlet")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncherirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
		EnchereManager enchereMgr = EnchereManager.getInstance();
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		ArticleVendu lArticle = null;
		Enchere lEnchere = null;
		Integer noArticle = Integer.parseInt(request.getParameter("article"));
		Date today = new Date();
		String message = "";
		
		try {
			lArticle = articleMgr.getArticleVenduById(noArticle);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		Integer enchere = Integer.parseInt(request.getParameter("enchere"));
		
		if(enchere > lArticle.getPrixVente() && lArticle.getDateFinEncheres().after(today)) {
			lEnchere = new Enchere(user.getNoUtilisateur(), noArticle, today, enchere);
			//lArticle.setPrixVente(enchere);
			try {
				enchereMgr.addEnchere(lEnchere, lArticle);
				lArticle.setPrixVente(enchereMgr.getMaxMontantByNoArticle(lEnchere));
				//articleMgr.updateArticleVendu(lArticle);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			message = "Votre enchère a bien été prise en compte";
			request.setAttribute("article", lArticle);
			request.setAttribute("message", message);
			request.setAttribute("classe", "valid");
			request.getRequestDispatcher("./detailsArticle").forward(request, response);;
		} else {
			if (enchere <= lArticle.getPrixVente())	message = "Votre enchère n'est pas assez élevée";
			if (lArticle.getDateFinEncheres().before(today)) message = "Les enchères sont terminées";
			request.setAttribute("message", message);
			request.setAttribute("classe", "error");
			request.setAttribute("article", lArticle);
			request.getRequestDispatcher("./detailsArticle").forward(request, response);
		}
	}

}
