package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bo.ArticleVendu;

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
		ArticleVendu lArticle = null;
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
			lArticle.setPrixVente(enchere);
			try {
				articleMgr.updateArticleVendu(lArticle);
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
			request.getRequestDispatcher("./detailsArticle").forward(request, response);;
		}
	}

}
