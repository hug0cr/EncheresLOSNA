package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.CategorieManager;
import fr.eni.encheresLOSNA.bo.Categorie;

/**
 * Servlet implementation class Controler
 */
@WebServlet("/Controler")
public class Controler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		CategorieManager categorieMgr = CategorieManager.getInstance();
		List<Categorie> categories = null;
		try {
			categories = categorieMgr.getCategories();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		application.setAttribute("categories", categories);
		request.getRequestDispatcher("./listeEncheres").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
