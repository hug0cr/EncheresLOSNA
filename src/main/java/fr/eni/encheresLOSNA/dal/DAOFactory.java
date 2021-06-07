/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

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
	
	public static CategorieDAO getCategorieDAO(){
		return new CategorieDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO(){
		return new UtilisateurDOAJdbcImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO(){
		return new ArticleVenduDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO(){
		return new RetraitDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO(){
		return new EnchereDAOJdbcImpl();
	}
}
