package fr.eni.encheresLOSNA.ihm;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.CategorieManager;
import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Categorie;
import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * Servlet implementation class GestionArticleServlet
 */
@WebServlet("/ArticleServlet")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String IMAGES_FOLDER = "/img";
	public String uploadPath;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
		Integer noArticle = Integer.parseInt(request.getParameter("article"));
		ArticleVendu lArticle = null;
		Categorie categorie = null;
		try {
			lArticle = articleMgr.getArticleVenduById(noArticle);
			int noCategorie = lArticle.getNoCategorie().intValue();
			categorie = CategorieManager.getInstance().getCategorieById(noCategorie);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		if (noArticle != null) {
			request.setAttribute("article", lArticle);
			request.setAttribute("categorie", categorie);
			try {
				request.getRequestDispatcher("./detailsArticle").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("./Controler").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = (String) request.getParameter("type");
		ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
		ArticleVendu article = createArticle(request, articleMgr);

		getImage(request);

		if (type.equals("crea")) {
			try {
				articleMgr.addArticleVendu(article);
			} catch (BLLException e) {
				request.setAttribute("message", "Une erreur est survenue");
				e.printStackTrace();
			}
		}
		if (type.equals("modif")) {
			try {
				articleMgr.updateArticleVendu(article);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("./Controler").forward(request, response);
	}

	/**
	 * @param request
	 * @return
	 */
	private ArticleVendu createArticle(HttpServletRequest request, ArticleVenduManager mgr) {
		ArticleVendu article = null;
		Date dateDebutEncheres = new Date();
		Date dateFinEncheres = new Date();
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		Integer noUser = user.getNoUtilisateur();
		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		Integer categorie = Integer.parseInt(request.getParameter("categorie"));
		Integer miseAPrix = Integer.parseInt(request.getParameter("prix"));
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateDebutEncheres = formatDate.parse(request.getParameter("dateDebut"));
			dateFinEncheres = formatDate.parse(request.getParameter("dateFin"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, noUser,
				categorie);
		return article;
	}

	private String getImage(HttpServletRequest request) throws IOException, ServletException {
		String pathImg = null;
		Part image = request.getPart("photo");
		String fileName = null;
		for (String content : image.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) fileName = content.substring(content.indexOf("=") + 2, content.length() - 1);
			else fileName = "default.file";
		}
		String fullPath = getServletContext().getRealPath(IMAGES_FOLDER) + File.separator + fileName;
		System.out.println(fullPath);
		if (!fileName.isEmpty()) {
			image.write(fullPath);
			pathImg = "img/" + fileName;
		}
		return pathImg;
	}
}
