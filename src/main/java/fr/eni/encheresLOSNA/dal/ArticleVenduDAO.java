/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

import fr.eni.encheresLOSNA.bo.ArticleVendu;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 19:46:18
 */
public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	public List<ArticleVendu> selectByKW(String keyWord) throws DALException;
	public List<ArticleVendu> selectEncheresEnCours() throws DALException;
	public List<ArticleVendu> selectEncheresEnCoursDUnUtilisateur(Integer noUtilisateur) throws DALException;
	public List<ArticleVendu> selectByCategorie(Integer noCategorie) throws DALException;
	public List<ArticleVendu> selectTop50() throws DALException;
}
