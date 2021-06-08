package fr.eni.encheresLOSNA.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * Servlet implementation class FiltrageServlet
 */
@WebServlet("/FiltrageServlet")
public class FiltrageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ENCHERES_OUVERTE = "encheresOuvertes",
			ENCHERES_EN_COURS = "encheresEnCours",
			ENCHERES_REMPORTEES = "encheresRemportees",
			VENTES_EN_COURS = "ventesEnCours",
			VENTES_NON_DEBUTEES = "ventesNonDebutees",
			VENTES_TERMINEES = "ventesTerminees";

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
		boolean hasOption = false;
		HttpSession session = null;
		session = request.getSession(false);
		Integer categorie = Integer.parseInt(request.getParameter("categorie"));
		String motCle = (String) request.getParameter("motCle");
		ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();

		List<ArticleVendu> lesArticlesParKW = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lesArticlesParCategorie = new ArrayList<ArticleVendu>();
		List<ArticleVendu> articlesToPrint = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lesArticlesParOption = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lesArticlesParCategorieEtKW = new ArrayList<ArticleVendu>();
		
		if (session.getAttribute("user") != null) {
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			String[] optionsVente = request.getParameterValues("vente");
			String[] optionsAchat = request.getParameterValues("achat");
			if (optionsAchat != null) {
				hasOption = true;
				for (String option : optionsAchat) {
					switch (option) {
					case ENCHERES_EN_COURS:
						try {
							lesArticlesParOption.addAll(articleMgr.getArticlesVentesEnCoursAvecEnchereUtilisateur(user.getNoUtilisateur()));
						} catch (BLLException e) {
							e.printStackTrace();
						}
						break;
					case ENCHERES_OUVERTE:
						try {
							lesArticlesParOption.addAll(articleMgr.getArticlesVentesEnCoursSansEnchereUtilisateur(user.getNoUtilisateur()));
						} catch (BLLException e) {
							e.printStackTrace();
						}
						break;
					case ENCHERES_REMPORTEES:
						System.out.println("Features à venir");
						break;
					default:
						break;
					}
				}
			} else if (optionsVente != null) {
				hasOption = true;
				for (String option : optionsVente) {
					switch (option) {
					case VENTES_EN_COURS:
						try {
							lesArticlesParOption.addAll(articleMgr.getArticlesVendusEnCoursByUtilisateur(user.getNoUtilisateur()));
						} catch (BLLException e) {
							e.printStackTrace();
						}
						break;
					case VENTES_NON_DEBUTEES:
						try {
							lesArticlesParOption.addAll(articleMgr.getArticlesVentesNonCommenceesByUtilisateur(user.getNoUtilisateur()));
						} catch (BLLException e) {
							e.printStackTrace();
						}
						break;
					case VENTES_TERMINEES:
						try {
							lesArticlesParOption.addAll(articleMgr.getArticlesVentesTermineeByUtilisateur(user.getNoUtilisateur()));
						} catch (BLLException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
			}
		}
		if (!hasOption) {
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
				articlesToPrint = customContains(lesArticlesParCategorie, lesArticlesParKW);
			}
			request.setAttribute("lesArticles", articlesToPrint);
		} else {
			if (categorie == 0 && motCle.equals("")) {
				try {
					articlesToPrint = customContains(lesArticlesParOption, articleMgr.getArticlesVendusTop50());
				} catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (categorie == 0) {
				try {
					articlesToPrint = customContains(lesArticlesParOption, articleMgr.getArticlesVendusByKW(motCle));
				} catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (motCle.equals("")) {
				try {
					articlesToPrint = customContains(lesArticlesParOption, articleMgr.getArticlesVendusByCategorie(categorie));
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
				lesArticlesParCategorieEtKW = customContains(lesArticlesParCategorie, lesArticlesParKW);
				articlesToPrint = customContains(lesArticlesParCategorieEtKW, lesArticlesParOption);
			}			
			request.setAttribute("lesArticles", articlesToPrint);
		}
		doGet(request, response);
	}
	
	private List<ArticleVendu> customContains(List<ArticleVendu> list1, List<ArticleVendu> list2) {
		List<ArticleVendu> articlesToPrint = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleL1 : list1) {
			for (ArticleVendu articleL2 : list2) {
				if (articleL1.getNoArticle() == articleL2.getNoArticle()) {
					articlesToPrint.add(articleL1);
				}
			}
		}
		return articlesToPrint;
	}

}
