/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

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
	 * Methode en charge de
	 * @param utilisateur
	 * @throws BLLException
	 */
	public void updateArticleVendu(ArticleVendu articleVendu) throws BLLException {
		try {
			validerArticleVendu(articleVendu);
			articleVenduDAO.update(articleVendu);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateArticleVendu-articleVendu:" + articleVendu, e);
		}
	}
	
	/**
	 * Methode en charge de
	 * @param id
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
	 * Methode en charge de
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
	 * Methode en charge de
	 * @param utilisateur
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
	 * Methode en charge de
	 * @param keyWord
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
	 * Methode en charge de
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusEnCours() throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectEncheresEnCours();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus en cours de vente ", e);
		}
		return articlesVendus;
	}
	

	/**
	 * Methode en charge de
	 * @param noUtilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> getArticlesVendusEnCoursByUtilisateur(Integer noUtilisateur) throws BLLException {
		List<ArticleVendu> articlesVendus = null;
		try {
			articlesVendus = articleVenduDAO.selectEncheresEnCoursDUnUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des articles vendus en cours de vente d'un utilisateur ", e);
		}
		return articlesVendus;
	}
	
	/**
	 * Methode en charge de
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
	 * Methode en charge de
	 * @param noCategorie
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
	 * Methode en charge de
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
//		if(a.getPrixVente() == null){
//			sb.append("La rue est obligatoire.\n");
//			valide = false;
//		}
//		if(a.getEtatVente() == null || a.getEtatVente().trim().length()==0){
//			sb.append("Le code postal est obligatoire.\n");
//			valide = false;
//		}
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
