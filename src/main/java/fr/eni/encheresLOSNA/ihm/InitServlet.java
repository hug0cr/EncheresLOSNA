/**
 * 
 */
package fr.eni.encheresLOSNA.ihm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import fr.eni.encheresLOSNA.bll.ArticleVenduManager;
import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.EnchereManager;
import fr.eni.encheresLOSNA.bll.EnchereRemporteeManager;
import fr.eni.encheresLOSNA.bll.UtilisateurManager;
import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 10 juin 2021 - 09:04:01
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -8889983490895816153L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() throws ServletException {
		Timer timer = new Timer();
		
		//////////// Task to run /////////////
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Hello world !");
				
				// Beginning //
				
				try {
					// Récupération de tout les articles du site
					List<ArticleVendu> articles = ArticleVenduManager.getInstance().getArticlesVendus();
					// Boucle sur tout les articles
					for (ArticleVendu articleVendu : articles) {
						// Si la date de fin d'enchère est dépassée
						if (articleVendu.getDateFinEncheres().before(new Date())) {
							System.out.println("Vente terminée");
							// Si l'enchère n'a toujours pas été ajoutée à la table ENCHERES_REMPORTEES
							if (!EnchereRemporteeManager.getInstance().isEnchereRemportee(articleVendu.getNoArticle())) {
								// Récupération de toutes les enchères sur l'article
								List<Enchere> encheres = EnchereManager.getInstance().getAllByNoArticle(articleVendu.getNoArticle());
								// Redonne les crédits à tout les enchérisseurs
								for (Enchere enchere : encheres) {
									Utilisateur u = UtilisateurManager.getInstance().getUtilisateurById(enchere.getNoUtilisateur());
									Integer creditACrediter = u.getCredit();
									UtilisateurManager.getInstance().updateCreditUtilisateur(enchere.getNoUtilisateur(), enchere.getMontantEnchere() + creditACrediter);
								}
								
							}
							
						} else {
							System.out.println("Vente non terminée");
						}
					}
					
				} catch (BLLException e) {
					e.printStackTrace();
				}
								
				// Ending //	
			}
		};
		//////////// End of task /////////////
		
		timer.scheduleAtFixedRate(task, 10, 10000);
	}
}
