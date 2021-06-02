/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

import fr.eni.encheresLOSNA.bo.ArticleVendu;

/**
 * @author Adrien
 *
 */
public interface ArticleVenduDAO extends DAO<ArticleVendu> {

	public List<ArticleVendu> selectByKW(String kw);
	public List<ArticleVendu> selectByCategorie(String categorie);
}
