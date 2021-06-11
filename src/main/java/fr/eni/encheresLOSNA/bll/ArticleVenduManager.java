/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.dal.ArticleVenduDAO;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 20:16:14
 */
public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	private static ArticleVenduDAO articleVenduDAO;
	
	/**
	 * Methode en charge de retourner la seule instance de la classe.
	 * Singleton
	 * @return
	 */
	public static ArticleVenduManager getInstance() {
		if (instance == null) instance = new ArticleVenduManager();
		return instance;
	}
	
	/**
	 * Constructeur
	 * @throws BLLException
	 */
	private ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	///////// Méthodes insert, update, selectById, selectAll, delete //////////
	
	/**
	 * Methode en charge de
	 * @param newUtilisateur
	 * @throws BLLException
	 */
	public void addArticleVendu(ArticleVendu newArticle) throws BLLException {
		if(newArticle.getNoArticle() != null) {
			throw new BLLException("Article déjà existant.");
		}
		try {
			validerArticleVendu(newArticle);
			articleVenduDAO.insert(newArticle);
		} catch (DALException e) {
			throw new BLLException("Echec addArticle", e);
		}
	}
	
	/**
	 * Methode en charge de modifier un article.
	 * @Constraint Empêche la modification d'un article en cours de vente.
	 * TODO la contrainte
	 * @param articleVendu
	 * @throws BLLException
	 */
	public void updateArticleVendu(ArticleVendu articleVendu) throws BLLException {
		try {
			Date maintenant = new Date(Calendar.getInstance().getTimeInMillis()); 
			if (maintenant.after(articleVendu.getDateDebutEncheres())) {
				throw new BLLException("Impossible de modifier un article dont la date de début d'enchère est dépassée.");
			}
			
			validerArticleVendu(articleVendu);
			articleVenduDAO.update(articleVendu);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateArticleVendu-articleVendu:" + articleVendu, e);
		}
	}
	
	/**
	 * Methode en charge de retourner un article selon son id.
	 * @param id de l'article
	 * @return
	 * @throws BLLException
	 */
	public ArticleVendu getArticleVenduById(int id) throws BLLException {
		ArticleVendu a = null;
		try {
			a = articleVenduDAO.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération article", e);
		}
		return a;
	}
	
	/**
	 * Methode en charge de retourner une liste contenant tout les articles du site.
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendus() throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus", e);
		}
		return articlesVendus;
	}

	
	/**
	 * Methode en charge d'effacer un article posté sur le site. 
	 * @param articleVendu 
	 * @throws BLLException
	 */
	public void removeArticleVendu(ArticleVendu articleVendu) throws BLLException{
		try {
			articleVenduDAO.delete(articleVendu);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article - ", e);
		}	
	}
	
	///////// Fin des méthodes de base //////////
	
	/**
	 * Methode en charge de retourner une liste de tout les articles contenant le mot-clé passer en paramètre
	 * dans leur nom ou leur description.
	 * @param keyWord le mot-clé pour effectuer la recherche
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusByKW(String keyWord) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		String keyWordTrimmed = keyWord.trim();
		keyWordTrimmed = keyWordTrimmed.toLowerCase();
		try {
			articlesVendus = articleVenduDAO.selectByKW(keyWordTrimmed);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus par mot-clé ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste de tout les articles en cours de vente sur le site
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusEnCours() throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesEnCoursDeVente();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus en cours de vente ", e);
		}
		return articlesVendus;
	}
	

	/**
	 * Methode en charge de retourner une liste contenant tout les articles en cours d'un vente d'un utilisateur.
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusEnCoursByUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesEnCoursDeVenteDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus en cours de vente d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste contenant les articles vendu d'un utilisateur dont la vente n'a pas commencée.
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVentesNonCommenceesByUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesVenteNonCommenceeDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles dont la vente n'a pas commencée d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste contenant les articles vendu d'un utilisateur dont la vente est terminée.
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVentesTermineeByUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesVenteTermineeDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles dont la vente est terminée d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste de tout les articles dont l'utilisateur a fait une enchère
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVentesEnCoursAvecEnchereUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesVenteEnCoursAvecEnchereDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles dont la vente est en cours avec une enchère d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste de tout les articles dont l'utilisateur n'a pas fait d'enchère
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVentesEnCoursSansEnchereUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesVenteEnCoursSansEnchereDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles dont la vente est en cours sans une enchère d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste contenant tout les articles dont l'utilisateur a remporté la vente
	 * @param noUtilisateur l'id de l'utilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesEnchereRemporteeParUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectArticlesEnchereRemporteeParUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles dont l'enchère à été remportée par l'utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste des 50 derniers articles ajoutés sur le site
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusTop50() throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectTop50();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération du top 50 des articles vendus ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de retourner une liste de tout les articles en ventes d'une catégorie.
	 * @param noCategorie le numéro id de la catégorie sur la TABLE CATEGORIE
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusByCategorie(Integer noCategorie) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectByCategorie(noCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus par categorie ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de mettre à jour le pruix de vente d'un article.
	 * @param noArticle
	 * @param prixVente
	 * @throws BLLException
	 */
	public void updatePrixVente(Integer noArticle, Integer prixVente) throws BLLException {
		try {
			articleVenduDAO.updatePrixVente(noArticle, prixVente);
			
		} catch (DALException e) {
			throw new BLLException("Echec updatePrixVente article id:" + noArticle, e);
		}
	}
	
	public String getPathPhotoById(Integer id) throws BLLException {
		String pathPhoto = null;
		try {
			pathPhoto = articleVenduDAO.selectPathPhotoById(id);
		} catch (DALException e) {
			throw new BLLException("Echec getPathPhotoById article id:" + id, e);
		}
		return pathPhoto;
	}
	
	/**
	 * Methode en charge de valider l'ajout ou la modification d'un article selon les règles définies.
	 * @param a
	 * @throws BLLException
	 */
	public void validerArticleVendu(ArticleVendu a) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(a==null){
			throw new BLLException("Utilisateur null");
		}
		
		//Les attributs des utilisateur sont obligatoires
		if(a.getNomArticle() == null || a.getNomArticle().trim().length()==0) {
			sb.append("Le nom de l'article est obligatoire.\n");
			valide = false;
		}
		if(a.getDescription() == null || a.getDescription().trim().length()==0) {
			sb.append("Une description  est obligatoire.\n");
			valide = false;
		}
		if(a.getDateDebutEncheres() == null){
			sb.append("Une date de début d'enchère est obligatoire.\n");
			valide = false;
		}
		if(a.getDateFinEncheres() == null){
			sb.append("Une date de fin d'enchère est obligatoire.\n");
			valide = false;
		}
		if(a.getMiseAPrix() == null){
			sb.append("Une mise à prix est obligatoire.\n");
			valide = false;
		}
		if(a.getNoUtilisateur() == null){
			sb.append("Un numéro d'utilisateur est obligatoire.\n");
			valide = false;
		}
		if(a.getNoCategorie() == null){
			sb.append("Un numéro de catégorie est obligatoire.\n");
			valide = false;
		}
		if (a.getDateFinEncheres().before(a.getDateDebutEncheres())) {
			sb.append("La date de début des enchères doit être antérieur à la date de fin des enchères.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
	
}
