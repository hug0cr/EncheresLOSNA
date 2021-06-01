/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Categorie;
import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.bo.Retrait;
import fr.eni.encheresLOSNA.bo.Utilisateur;
import fr.eni.encheresLOSNA.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.encheresLOSNA.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheresLOSNA.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.encheresLOSNA.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.encheresLOSNA.dal.jdbc.UtilisateurDOAJdbcImpl;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:10:20
 */
public class DAOFactory {
	
	public static DAO<Categorie> getCategorieDAO(){
		return new CategorieDAOJdbcImpl();
	}
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDOAJdbcImpl();
	}
	
	public static DAO<ArticleVendu> getArticleVenduDAO(){
		return new ArticleVenduDAOJdbcImpl();
	}
	
	public static DAO<Retrait> getRetraitDAO(){
		return new RetraitDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO(){
		return new EnchereDAOJdbcImpl();
	}
}
